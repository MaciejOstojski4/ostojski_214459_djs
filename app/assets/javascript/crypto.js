$(function() {
    const fetchCryptoValue = function() {
        if($("#crypto-shortcut").length > 0) {
            const cryptoShortcut = $("#crypto-shortcut").data("shortcut");
            $.ajax({
                type: 'GET',
                url: 'https://min-api.cryptocompare.com/data/price?fsym=' + cryptoShortcut + '&tsyms=USD'
            })
            .done(function(response) {
                $("#crypto-value").text("Actual value: $" +response.USD);
            })
            .catch(function(error) {
                console.log(error);
                $("#crypto-value").text("Cannot find selected cryptocurrency actual value in API");
            })
        }
    }

    fetchCryptoValue();
})