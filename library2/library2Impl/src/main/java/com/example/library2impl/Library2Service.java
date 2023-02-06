package com.example.library2impl;

import com.example.library2api.ILibrary2Service;

public class Library2Service implements ILibrary2Service {

    @Override
    public String getServiceName() {
        return "Library2Service";
    }
}
