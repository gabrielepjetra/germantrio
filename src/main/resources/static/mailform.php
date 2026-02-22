<?php
// Cambia con la tua email reale
$to = "r0744079@student.thomasmore.be";

// Controllo metodo
if ($_SERVER["REQUEST_METHOD"] !== "POST") {
    header("Location: contact.html");
    exit;
}

// Recupero dati e sanitizzazione base
$name    = trim($_POST["name"] ?? "");
$email   = trim($_POST["email"] ?? "");
$message = trim($_POST["message"] ?? "");

// Validazione minima
if ($name === "" || $email === "" || $message === "") {
    echo "All fields are required.";
    exit;
}

if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
    echo "Invalid email address.";
    exit;
}

// Costruzione email
$subject = "New contact from The German Trio";
$headers = "From: $name <$email>\r\n";
$headers .= "Reply-To: $email\r\n";
$headers .= "Content-Type: text/plain; charset=UTF-8";

$body = "Name: $name\n";
$body .= "Email: $email\n\n";
$body .= "Message:\n$message";

// Invio
if (mail($to, $subject, $body, $headers)) {
    header("Location: about.html");
    exit;
} else {
    echo "Error sending the message.";
}