package com.wavefront.exception;

/**
 * this class is used to build the error response
 * in json format.
 *
 */
public class ApiError {

    /** error message **/
    private String message;

    /** error tag **/
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ApiError() {
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return  message;
    }
}
