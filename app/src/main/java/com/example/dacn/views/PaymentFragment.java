package com.example.dacn.views;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.braintreepayments.api.dropin.DropInActivity;
import com.braintreepayments.api.dropin.DropInRequest;
import com.braintreepayments.api.dropin.DropInResult;
import com.braintreepayments.api.interfaces.HttpResponseCallback;
import com.braintreepayments.api.internal.HttpClient;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.example.dacn.R;
import com.example.dacn.adapters.CartListAdapter;
import com.example.dacn.databinding.FragmentPaymentBinding;
import com.example.dacn.models.CartItem;
import com.example.dacn.viewmodels.ShopViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentFragment extends Fragment implements CartListAdapter.CartInterface{
    NavController navController;
    FragmentPaymentBinding fragmentPaymentBinding;
    ShopViewModel shopViewModel;

    private static final int REQUEST_CODE = 1234;
    final String API_GET_TOKEN = "http://10.0.2.2:8080/braintree/main.php";
    final String API_CHECK_OUT = "http://10.0.2.2:8080/braintree/checkout.php";

    String token, amount;
    HashMap<String, String> paramsHash;

    Button payNowButton;
    TextView paymentTotalTextView;

    public PaymentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentPaymentBinding = FragmentPaymentBinding.inflate(inflater, container, false);
        return fragmentPaymentBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        final CartListAdapter cartListAdapter = new CartListAdapter(this);
        fragmentPaymentBinding.paymentRecyclerView.setAdapter(cartListAdapter);
        fragmentPaymentBinding.paymentRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));

        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        shopViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                cartListAdapter.submitList(cartItems);
                fragmentPaymentBinding.payNowButton.setEnabled(cartItems.size() > 0);
            }
        });

        shopViewModel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                fragmentPaymentBinding.paymentTotalTextView.setText(aDouble.toString());
            }
        });

        RadioButton rb1 = (RadioButton) getView().findViewById(R.id.radioButton);
        RadioButton rb2 = (RadioButton) getView().findViewById(R.id.radioButton2);
        paymentTotalTextView=(TextView) getView().findViewById(R.id.paymentTotalTextView);
        payNowButton=(Button) getView().findViewById(R.id.payNowButton);

        fragmentPaymentBinding.payNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rb1.isChecked()) {
                    shopViewModel.resetCart();
                    navController.navigate(R.id.action_paymentFragment_to_orderFragment);
                } else if (rb2.isChecked()) {
                    new getToken().execute();
                    submitPayment();
                } else if(!rb1.isChecked() && !rb2.isChecked())
                    Toast.makeText(getActivity(), "Vui lòng chọn phương thức thanh toán", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void submitPayment(){
        String payValue=paymentTotalTextView.getText().toString();
        if(!payValue.isEmpty())
        {
            DropInRequest dropInRequest=new DropInRequest().clientToken(token);
            startActivityForResult(dropInRequest.getIntent(getActivity()),REQUEST_CODE);
        }
        else
            Toast.makeText(getActivity(), "Nhập số tiền hợp lệ", Toast.LENGTH_SHORT).show();

    }

    public void sendPayments(){
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, API_CHECK_OUT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.toString().contains("Successful")){
                            shopViewModel.resetCart();
                            Toast.makeText(getActivity(), "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                            navController.navigate(R.id.action_paymentFragment_to_orderFragment);
                        }
                        else {
                            Toast.makeText(getActivity(), "Thanh toán thất bại", Toast.LENGTH_SHORT).show();
                        }
                        Log.d("Response",response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", error.toString());
            }
        }){
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                if(paramsHash==null)
                    return null;
                Map<String,String> params=new HashMap<>();
                for(String key:paramsHash.keySet())
                {
                    params.put(key,paramsHash.get(key));
                }
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("Content-type","application/x-www-form-urlencoded");
                return params;
            }
        };
        RetryPolicy mRetryPolicy=new DefaultRetryPolicy(0,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(mRetryPolicy);
        queue.add(stringRequest);
    }

    public class getToken extends AsyncTask {
        ProgressDialog mDailog;

        @Override
        public Object doInBackground(Object[] objects) {
            HttpClient client=new HttpClient();
            client.get(API_GET_TOKEN, new HttpResponseCallback() {
                @Override
                public void success(String responseBody) {
                    mDailog.dismiss();
                    token=responseBody;
                }
                @Override
                public void failure(Exception exception) {
                    mDailog.dismiss();
                    Log.d("Error",exception.toString());
                }
            });
            return null;
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            mDailog=new ProgressDialog(getActivity(),android.R.style.Theme_DeviceDefault_Light_Dialog);
            mDailog.setCancelable(false);
            mDailog.setMessage("Xin vui lòng chờ...");
            mDailog.show();
        }

        @Override
        public void onPostExecute(Object o){
            super.onPostExecute(o);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                DropInResult result = data.getParcelableExtra(DropInResult.EXTRA_DROP_IN_RESULT);
                PaymentMethodNonce nonce = result.getPaymentMethodNonce();
                String strNounce = nonce.getNonce();
                if (!paymentTotalTextView.getText().toString().isEmpty()) {
                    amount = paymentTotalTextView.getText().toString();
                    paramsHash = new HashMap<>();
                    paramsHash.put("amount", amount);
                    paramsHash.put("nonce", strNounce);

                    sendPayments();
                } else {
                    Toast.makeText(getActivity(), "Nhập số tiền hợp lệ", Toast.LENGTH_SHORT).show();
                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getActivity(), "Đã hủy", Toast.LENGTH_SHORT).show();
            } else {
                Exception error = (Exception) data.getSerializableExtra(DropInActivity.EXTRA_ERROR);
                Log.d("Error", error.toString());
            }
        }
    }
    @Override
    public void deleteItem(CartItem cartItem) {
        shopViewModel.removeItemFromCart(cartItem);
    }

    @Override
    public void changeQuantity(CartItem cartItem, int quantity) {
        shopViewModel.changeQuantity(cartItem, quantity);
    }
}