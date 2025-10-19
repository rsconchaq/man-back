package com.javainuse.bootmysqlcrud.wrapper;

public class ResponseWrapper<T> {
    public boolean success;

    public String message;

    public String additional;

    public T data;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public String getMessage() {
        return this.message;
    }

    public String getAdditional() {
        return this.additional;
    }

    public T getData() {
        return this.data;
    }

    public ResponseWrapper() {}

    public ResponseWrapper(T data, String mensaje) {
        this.success = true;
        this.message = mensaje;
        this.data = data;
    }

    public ResponseWrapper(String mensaje) {
        this.success = false;
        this.message = mensaje;
        this.data = null;
    }

    public ResponseWrapper(String mensaje, String additional) {
        this.success = false;
        this.message = mensaje;
        this.additional = additional;
        this.data = null;
    }
}
