package com.barter.barter;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class S3key {
    public String accessKey = "";
    public String secretKey ="";
    public String bucket = "";
    public String region ="";

}
