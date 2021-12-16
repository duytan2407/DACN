<?php
session_start();
require_once("lib/autoload.php");
if(file_exists(__DIR__."/../.env"))
{
    $dotenv= new Dotenv\Dotenv(__DIR__ . "/../");
    $dotenv->load();
}
Braintree\Configuration::environment("sandbox");
Braintree\Configuration::merchantId("8twhvdt9np7q4wch");
Braintree\Configuration::publicKey("4sw33tz4hxk4y4wf");
Braintree\Configuration::privateKey("d310307dbb9735356d1071b67acc9468");
?>