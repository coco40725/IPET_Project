package com.core.entities;

import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author Yu-Jing
 * @create 2023-01-22-下午 10:24
 */

@Data
public class Core implements Serializable {
    @Serial
    private static final long serialVersionUID = 6746558969233907473L;
    private boolean successful;
    private String message;

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
