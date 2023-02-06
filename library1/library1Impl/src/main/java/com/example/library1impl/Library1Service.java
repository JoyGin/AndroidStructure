package com.example.library1impl;

import com.example.library1api.ILibrary1Service;

public class Library1Service implements ILibrary1Service {

    @Override
    public String getServiceName() {
        return "Library1Service";
    }
}
