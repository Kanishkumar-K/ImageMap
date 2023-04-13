<?php
function encrypt($text, $shift) {
  $result = "";
  $text = strtolower($text);
  $text_len = strlen($text);
  
  for($i = 0; $i < $text_len; $i++) {
    $char = ord($text[$i]);
    if($char >= 97 && $char <= 122) {
      $char = ($char + $shift - 97) % 26 + 97;
    }
    $result .= chr($char);
  }
  
  return $result;
}

function decrypt($text, $shift) {
  $result = "";
  $text = strtolower($text);
  $text_len = strlen($text);
  
  for($i = 0; $i < $text_len; $i++) {
    $char = ord($text[$i]);
    if($char >= 97 && $char <= 122) {
      $char = ($char - $shift - 97 + 26) % 26 + 97;
    }
    $result .= chr($char);
  }
  
  return $result;
}

// Example usage
$text = "aBzio";
$shift = 3;
$encrypted_text = encrypt($text, $shift);
$decrypted_text = decrypt($encrypted_text, $shift);

echo "CEASER CIPHER PROGRAM<br><br>";
echo "Original text:<br> $text<br><br>";
echo "Encrypted text:<br>$encrypted_text<br><br>";
echo "Decrypted text:<br> $decrypted_text";
?>
