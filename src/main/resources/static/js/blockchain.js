let spot = {

    init: function() {
        $("#btnLimitLong").on("click", ()=> {
            this.btnLimitLong();
        });

        $("#btnLimitShort").on("click", ()=> {
            this.btnLimitShort();
        });

        $("#btnMarketLong").on("click", ()=> {
            this.btnMarketLong();
        });

        $("#btnMarketShort").on("click", ()=> {
            this.btnMarketShort();
        });
    },

    btnLimitLong: function() {
        console.log("지정가 LONG 버튼이 눌렸다.");
    },

    btnLimitShort: function() {
        console.log("지정가 Short 버튼이 눌렸다.");
    },

    btnMarketLong: function() {
        console.log("시장가 LONG 버튼이 눌렸다.");
    },

    btnMarketShort: function() {
        console.log("시장가 Short 버튼이 눌렸다.");
    },
}

spot.init();