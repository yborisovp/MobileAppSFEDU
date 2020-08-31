package com.example.sfedymob.supporting_functions.hash_functions;

import org.bouncycastle.jcajce.provider.BouncyCastleFipsProvider;

import java.security.Security;


public class Create_and_Send_Email_Hash {
    private void create_Provider() {
        Security.addProvider(new BouncyCastleFipsProvider());
    }
}
