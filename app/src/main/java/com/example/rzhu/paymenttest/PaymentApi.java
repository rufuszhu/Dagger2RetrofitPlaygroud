package com.example.rzhu.paymenttest;

import com.squareup.okhttp.*;

import org.json.JSONObject;

import dagger.Provides;
import hugo.weaving.DebugLog;
import retrofit.*;
import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by rzhu on 10/22/2015.
 */
public interface PaymentApi {
    @FormUrlEncoded
    @POST("/HostPayService/v1/hostpay/transactions/")
    Call<OTKResponse> OTKRequest(@Field("account_token") String account_token,
                              @Field("protocol_version") String protocol_version,
                              @Field("transaction_type") String CREDIT_CARD,
                              @Field("manage_payer_data") String manage_payer_data,
                              @Field("charge_type") String charge_type,
                              @Field("charge_total") String charge_total,
                              @Field("background-color") String background_color,
                              @Field("customer_information_visible") String customer_information_visible,
                              @Field("order_information_visible") String order_information_visible,
                              @Field("bill_customer_title_visible") String bill_customer_title_visible,
                              @Field("bill_first_name_visible") String bill_first_name_visible,
                              @Field("bill_middle_name_visible") String bill_middle_name_visible,
                              @Field("bill_last_name_visible") String bill_last_name_visible,
                              @Field("bill_company_visible") String bill_company_visible,
                              @Field("bill_address_one_visible") String bill_address_one_visible,
                              @Field("bill_address_two_visible") String bill_address_two_visible,
                              @Field("bill_city_visible") String bill_city_visible,
                              @Field("bill_state_or_province_visible") String bill_state_or_province_visible,
                              @Field("bill_country_code_visible") String bill_country_code_visible,
                              @Field("bill_postal_code_visible") String bill_postal_code_visible,
                              @Field("bill_address_one_mandatory") String bill_address_one_mandatory,
                              @Field("bill_address_two_mandatory") String bill_address_two_mandatory,
                              @Field("bill_city_mandatory") String bill_city_mandatory,
                              @Field("bill_state_or_province_mandatory") String bill_state_or_province_mandatory,
                              @Field("bill_country_code_mandatory") String bill_country_code_mandatory,
                              @Field("bill_postal_code_mandatory") String bill_postal_code_mandatory,
                              @Field("charge_type_row_visible") String charge_type_row_visible,
                              @Field("charge_total_row_visible") String charge_total_row_visible,
                              @Field("charge_total_visible") String charge_total_visible,
                              @Field("shipping_address_one_mandatory") String shipping_address_one_mandatory,
                              @Field("shipping_address_two_mandatory") String shipping_address_two_mandatory,
                              @Field("order_id") String order_id,
                              @Field("card_brand") String card_brand,
                              @Field("personal_identification_number_visible") String personal_identification_number_visible,
                              @Field("credit_card_verification_number_mandatory") String credit_card_verification_number_mandatory,
                              @Field("result_response_code_visible") String result_response_code_visible,
                              @Field("result_approved_amount_visible") String result_approved_amount_visible,
                              @Field("result_charge_total_visible") String result_charge_total_visible,
                              @Field("result_order_id_visible") String result_order_id_visible,
                              @Field("result_transaction_type_label") String result_transaction_type_label,
                              @Field("result_bank_approval_code_visible") String result_bank_approval_code_visible,
                              @Field("result_transaction_type_visible") String result_transaction_type_visible,
                              @Field("result_requested_amount_visible") String result_requested_amount_visible,
                              @Field("submit_button_label") String submit_button_label);


    @FormUrlEncoded
    @POST("/api/v1/transactions")
    Call<ResponseBody> CheckTokenRequest(@Field("account_token") String account_token,
                                        @Field("order_id") String order_id,
                                        @Field("charge_type") String charge_type,
                                        @Field("transaction_type") String transaction_type,
                                        @Field("full_detail_flag") String full_detail_flag);

}
