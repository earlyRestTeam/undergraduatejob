package com.lyx.undergraduatejob.config;



public class AliPayConfig {
    public final String APP_ID = "2016101600701148";
    public final String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCku5pRbMokwW29Eq4bNeJ4yzkyiUq9hvkvTnDAJgDd85mROH/0LFbHsmq+rX5OQmHcZkXWe04658NmOq55KVsA1/Z4EpiZ8ycXCg8ChXRea3MEghabfaHvq99c0PwDz2x78tKvyjl1UCt/fvtlSx1nPGjqWPJSVbbq6JkIAc+gMUiahQ9+ITSmXX3MSP04h5Kggj8v34L5krPN1Bh+irUgmXH4hYxzBYSW/6IArPHYNFop2k/gnd4lpdWPik33mjH3pzsKxhU+J2GS6C4anRXyOmamEpkbLkBlUEBJJScqeQvV9hSXqPlF8Fpu8zGtcgMcFssR718fWxp8V8MFpBb7AgMBAAECggEAPRZwuIc+QvkT58x4khCeZir+j/YHqFbYKynMeATfx27hYM/x6ytSFwXbvc/6iazmO2QVu97H+OIfPNbAAqBKZfqwJIDI5DYeJdYOEOJKcAUqht5y4cBp/8Xds76v5f3SffhjFttICW/sYYBGW57KTZFCWr0XR18P0PbmYOjDBOUI5rV5rBXed8VGhrzclyB9X6ht5WRca9s6OK44AJmowFM6UlfiQ5/PcTYN0e98WEv7/otGvHgcofyOUjMg7cnQKfbqjFSpCWeRs5hIB+Ts+Mk7t2nxSgAbQ/RhO50HTrqnrs1skHog7/nOHV5Wtqg+Mn+/SuziEcHeI4pVf1HWgQKBgQDf2/dvUp1kRjPqv2ZbOSpAwUmu09az6Vxc6MQhygaCaWJnKcmyPPSrPpdIbbaq88ZYU0N2VtXGEC4doAuwou3aBntIVubfslWGLu6jSsAsLTuiT36GcylNTp8YNu4JcbPHe1gtY2/vjPAE9FRiyfFWcE2xR7AvSU8ZlCGL05n/wQKBgQC8Ymv0vS3wBclAVvmf1pZ7FrQzz/85pAutXeEHadjCP20dhRc3Eh0T8agpn/fHwMpymzKQ8VmwhARBbyh1UG5M9+QtPFVh9Jo3jEbdBoXNEXpiiWV+/u21n70ptugX4MyH6YP8hnOyF4XQ05fzKq3dkjMn8LFh7XQRHTZXdAeFuwKBgEM4CtmLpiU5C1fe9z5HMQ/WumhbxemqFgj0AAjhYAkC2gB+RMlXkmRlXAnsn+A/aPyWq/g55OEfdKmCqF/23JMORTCYn6TmKNYs+41e4cuiHXkEg8qdg/ZpuE4OGHYPhVtOB1FBvZfh6yShtUEgwG4jNMEZ9KaOTckxeM1C0chBAoGAOz4weRztT2hia+tuQUf4tuji7tBgkT33tiOp2uz8j8nmhLwQGu+FWXpLyhtWjukgQX5xHofoDszVkcZlUM9kgKwMg/VPXKjRvtpMhsHAXkc617YhxxhgpReBI5cz0h6N88gKC+Y0iDNBj/Sa+ovXdbC+ibb18bf9IkyvhD0nCHUCgYAH+3SZLX2qL05rCutZTCMRUFvL094pDZBKYXtWmuRgSKR7nquz9agP8d+XhY0n84ZNGYn9TrWGPuKE2lBn58wmqjTrnFT/VtVYAu2+oPZLBeJuwNKsXxRU2A42qHJsISPvv6VHxd/Kp04yvnZgW3WNwtetl4NIS5XrAQ7ycQ7iKg==";
    public final String CHARSET = "UTF-8";
    public final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnSvrbOfheDw7t+HbttyW8rgn5iRnlky81yy+Tvlm+iOKC9Ob4v0qQvc2vrr+nvgxWgUtwsnCccft/rZlUcFdBVEbIYjJBqsGYLgNJbSFuOFIny+8Kwf9rvJqePIgMKJUFYv3UPBKM39lNuwGMd/vJuEVO6DdVK74/uZfct3Lg4G10ciCYpx43hCOHQ5h5KGI3SCNAcFKavoqgS2e/EnyMHBHMrMCD2qhdsD+RmmT/bd2GpVKFwH1gM2RNEgY8KY/9xEA8pckQsFV4Pgx5VmsQiL1Eqip+UA7x0MWxBXsbpJwswMptMZCVTWZxYMhHdSu5+Wxyca3AIUNqNhQwlHODwIDAQAB";
    //这是沙箱接口路径,正式路径为https://openapi.alipay.com/gateway.do
    public final String GATEWAY_URL ="https://openapi.alipaydev.com/gateway.do";
    public final String FORMAT = "JSON";
    //签名方式
    public final String SIGN_TYPE = "RSA2";
    //支付宝异步通知路径,付款完毕后会异步调用本项目的方法,必须为公网地址
    public final String NOTIFY_URL = "http://127.0.0.1:8080/user/notifyUrl";
    //支付宝同步通知路径,也就是当付款完毕后跳转本项目的页面,可以不是公网地址
    public final String RETURN_URL = "http://127.0.0.1:8080/user/returnUrl";
}
