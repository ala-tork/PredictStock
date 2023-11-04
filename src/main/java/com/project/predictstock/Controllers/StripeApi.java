package com.project.predictstock.Controllers;


import com.project.predictstock.DTO.Stripe.*;
import com.project.predictstock.Services.StripeService.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Subscription;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/payment")
@AllArgsConstructor
public class StripeApi {

    private final StripeService stripeService;

    @PostMapping("/card/token")
    @ResponseBody
    public StripeTokenDto createCardToken(@RequestBody StripeTokenDto model){

        return stripeService.CreateCardToekn(model);
    }

    @PostMapping("/charge")
    @ResponseBody
    public StripeChargeDto charge (@RequestBody StripeChargeDto model){
        return stripeService.charge(model);
    }


    @PostMapping("/payment")
    public ResponseEntity<String> payment(@RequestBody PaymentRequest paymentRequest) {
        try {
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", paymentRequest.getAmount());
            chargeParams.put("currency", "usd");
            chargeParams.put("source", paymentRequest.getToken());
            chargeParams.put("description", paymentRequest.getDescription());

            Charge charge = Charge.create(chargeParams);

            return ResponseEntity.ok("Payment successful: " + charge.getId());
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment failed: " + e.getMessage());
        }
    }
    /// payment example :
//    {
//        "stripeToken": "tok_1O6CTiLI8x8tRczcJ1ZSC9RE",
//            "username": "ala",
//            "amount": 99,
//            "additionalInfo": {
//        "ID_TAG": "25467954650"
//    }
//    }



    //others
    @PostMapping("/customer/subscription")
    @ResponseBody
    public StripeSubscriptionResponse subscription(@RequestBody StripeSubscriptionDto model) {

        return stripeService.createSubscription(model);
    }

    @DeleteMapping("/subscription/{id}")
    @ResponseBody
    public SubscriptionCancelRecord cancelSubscription(@PathVariable String id){

        Subscription subscription = stripeService.cancelSubscription(id);
        if(nonNull(subscription)){

            return new SubscriptionCancelRecord(subscription.getStatus());
        }

        return null;
    }

}
