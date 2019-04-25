package com.springretry;

/*
import java.util.Collections;
import java.util.Date;

import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
*/

/**
 * Hello world!
 *
 */
public class App 
{
    /*
    
    public static void main(String[] args) 
    {
        final RetryTemplate retryTemplate = new RetryTemplate();
        final SimpleRetryPolicy policy = new SimpleRetryPolicy(3, 
                Collections.<Class<? extends Throwable>,
                Boolean>singletonMap(Exception.class, true));
        ExponentialBackOffPolicy exponentialBackOffPolicy = new ExponentialBackOffPolicy();
        exponentialBackOffPolicy.setInitialInterval(2000);
        exponentialBackOffPolicy.setMultiplier(3);
        exponentialBackOffPolicy.setMaxInterval(5000);
        retryTemplate.setRetryPolicy(policy);
        retryTemplate.setBackOffPolicy(exponentialBackOffPolicy);

        final RetryCallback<String, Exception> retryCallback = new RetryCallback<String, Exception>() {
            public String doWithRetry(RetryContext context) throws Exception {
                System.out.println(new Date());
                System.out.println("retryCallback");
                try {
                    String [] str = new String [2];
                    str[3] = "";
                } catch (Exception e) {
                    // TODO: handle exception
                }
                
                return "1";
            }
        };

        final RecoveryCallback<String> recoveryCallback = new RecoveryCallback<String>() {
            public String recover(RetryContext context) throws Exception {
                System.out.println("recoveryCallback");
                return null;
            }
        };

        try {
            System.out.println("retryTemplate execute start");
            String response = retryTemplate.execute(retryCallback, recoveryCallback);
            System.out.println("retryTemplate execute end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
}
