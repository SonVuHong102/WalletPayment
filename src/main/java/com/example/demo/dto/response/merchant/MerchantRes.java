package com.example.demo.dto.response.merchant;

import com.example.demo.dto.response.IResponseData;
import com.example.demo.model.Merchant;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Data
public class MerchantRes implements IResponseData {
    Merchant merchant;
}
