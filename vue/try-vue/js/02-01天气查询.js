var app = new Vue({
    el: "#app",
    data: {
        city: "",
        weatherList: []
    },
    methods: {
        searchWeather: function() {
            if (this.city == "") {
                this.weatherList = [];
            }

            var thisApp = this;
            axios.get("http://wthrcdn.etouch.cn/weather_mini?city=" + this.city)
                .then(function (response) {
                    // console.log(response);
                    if (response.status == 200 && response.data.status == 1000) {
                        thisApp.weatherList = response.data.data.forecast;
                    } else {
                        thisApp.weatherList = [];
                    }
                })
                .catch(function (error) {
                    // console.log(error);
                    thisApp.weatherList = [];
                });
        },
        changeCity: function(city) {
            this.city = city;
            this.searchWeather();
        }
    }
})