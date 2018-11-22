package com.example.danie.myapplication;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;


public class SecondActivity extends AppCompatActivity {

    byte[] decryptedCipherText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final TextView textView = findViewById(R.id.textView);
        Intent i = getIntent();
        String msg = i.getStringExtra("dataInEditText");

        final TextView decryptedTextView = findViewById(R.id.textView6);


        byte[] encryptionKey = "MZygpewJsCpRrfOr".getBytes(StandardCharsets.UTF_8);
        byte[] plainText = msg.getBytes(StandardCharsets.UTF_8);
        AdvancedEncryption advancedEncryptionStandard = new AdvancedEncryption(encryptionKey);
        try {
            final byte[] cipherText = advancedEncryptionStandard.encrypt(plainText);
            textView.setText(new String(cipherText));
            final Button copy = findViewById(R.id.button3);
            copy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("cipherText", new String(cipherText));
                    clipboard.setPrimaryClip(clip);
                }
            });
            decryptedCipherText = advancedEncryptionStandard.decrypt(cipherText);
            decryptedTextView.setText(new String(decryptedCipherText));



        } catch(Exception e){
            e.printStackTrace();
        }



    }




    public class AdvancedEncryption {
        private byte[] key;
        private static final String ALGORITHM = "AES";

        public AdvancedEncryption(byte[] key) {
            this.key = key;
        }

        /**
         * Encrypts the given plain text
         * @param plainText The message to be encrypted
         * @throws Exception
         */
        public byte[] encrypt(byte[] plainText) throws Exception {
            SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            return cipher.doFinal(plainText);

        }

        /**
         * Decrypts the given byte array
         *
         * @param cipherText The data to decrypt
         */
        public byte[] decrypt(byte[] cipherText) throws Exception
        {
            SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            return cipher.doFinal(cipherText);
        }

    }

}