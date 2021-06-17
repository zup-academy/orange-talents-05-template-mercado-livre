package com.mercado.mercadolivre.util;

public enum Gateway {
    
    PayPal {
        @Override
        public String getUrl(String codigoCompra) {
            return "paypal.com?buyerId="+ codigoCompra +"&redirectUrl=http://localhost:8080/produtos/comprar/paypal";
        }
    },
    PagSeguro {
        @Override
        public String getUrl(String codigoCompra) {
            return "pagseguro.com?buyerId="+ codigoCompra +"&redirectUrl=http://localhost:8080/produtos/comprar/transacao/pagseguro";
        }
    };

    public abstract String getUrl(String purchaseCode);
}
