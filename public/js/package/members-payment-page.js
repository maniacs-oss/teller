!function(e){function t(r){if(n[r])return n[r].exports;var a=n[r]={exports:{},id:r,loaded:!1};return e[r].call(a.exports,a,a.exports,t),a.loaded=!0,a.exports}var n={};return t.m=e,t.c=n,t.p="",t(0)}([function(e,t,n){e.exports=n(54)},,,function(e,t){"use strict";t.__esModule=!0,t["default"]=function(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}},function(e,t,n){"use strict";function r(e){return e&&e.__esModule?e:{"default":e}}t.__esModule=!0;var a=n(5),o=r(a);t["default"]=function(){function e(e,t){for(var n=0;n<t.length;n++){var r=t[n];r.enumerable=r.enumerable||!1,r.configurable=!0,"value"in r&&(r.writable=!0),(0,o["default"])(e,r.key,r)}}return function(t,n,r){return n&&e(t.prototype,n),r&&e(t,r),t}}()},function(e,t,n){e.exports={"default":n(6),__esModule:!0}},function(e,t,n){n(7);var r=n(10).Object;e.exports=function(e,t,n){return r.defineProperty(e,t,n)}},function(e,t,n){var r=n(8);r(r.S+r.F*!n(18),"Object",{defineProperty:n(14).f})},function(e,t,n){var r=n(9),a=n(10),o=n(11),i=n(13),u="prototype",s=function(e,t,n){var c,l,f,d=e&s.F,p=e&s.G,v=e&s.S,h=e&s.P,y=e&s.B,_=e&s.W,m=p?a:a[t]||(a[t]={}),$=m[u],g=p?r:v?r[t]:(r[t]||{})[u];p&&(n=t);for(c in n)l=!d&&g&&void 0!==g[c],l&&c in m||(f=l?g[c]:n[c],m[c]=p&&"function"!=typeof g[c]?n[c]:y&&l?o(f,r):_&&g[c]==f?function(e){var t=function(t,n,r){if(this instanceof e){switch(arguments.length){case 0:return new e;case 1:return new e(t);case 2:return new e(t,n)}return new e(t,n,r)}return e.apply(this,arguments)};return t[u]=e[u],t}(f):h&&"function"==typeof f?o(Function.call,f):f,h&&((m.virtual||(m.virtual={}))[c]=f,e&s.R&&$&&!$[c]&&i($,c,f)))};s.F=1,s.G=2,s.S=4,s.P=8,s.B=16,s.W=32,s.U=64,s.R=128,e.exports=s},function(e,t){var n=e.exports="undefined"!=typeof window&&window.Math==Math?window:"undefined"!=typeof self&&self.Math==Math?self:Function("return this")();"number"==typeof __g&&(__g=n)},function(e,t){var n=e.exports={version:"2.2.0"};"number"==typeof __e&&(__e=n)},function(e,t,n){var r=n(12);e.exports=function(e,t,n){if(r(e),void 0===t)return e;switch(n){case 1:return function(n){return e.call(t,n)};case 2:return function(n,r){return e.call(t,n,r)};case 3:return function(n,r,a){return e.call(t,n,r,a)}}return function(){return e.apply(t,arguments)}}},function(e,t){e.exports=function(e){if("function"!=typeof e)throw TypeError(e+" is not a function!");return e}},function(e,t,n){var r=n(14),a=n(22);e.exports=n(18)?function(e,t,n){return r.f(e,t,a(1,n))}:function(e,t,n){return e[t]=n,e}},function(e,t,n){var r=n(15),a=n(17),o=n(21),i=Object.defineProperty;t.f=n(18)?Object.defineProperty:function(e,t,n){if(r(e),t=o(t,!0),r(n),a)try{return i(e,t,n)}catch(u){}if("get"in n||"set"in n)throw TypeError("Accessors not supported!");return"value"in n&&(e[t]=n.value),e}},function(e,t,n){var r=n(16);e.exports=function(e){if(!r(e))throw TypeError(e+" is not an object!");return e}},function(e,t){e.exports=function(e){return"object"==typeof e?null!==e:"function"==typeof e}},function(e,t,n){e.exports=!n(18)&&!n(19)(function(){return 7!=Object.defineProperty(n(20)("div"),"a",{get:function(){return 7}}).a})},function(e,t,n){e.exports=!n(19)(function(){return 7!=Object.defineProperty({},"a",{get:function(){return 7}}).a})},function(e,t){e.exports=function(e){try{return!!e()}catch(t){return!0}}},function(e,t,n){var r=n(16),a=n(9).document,o=r(a)&&r(a.createElement);e.exports=function(e){return o?a.createElement(e):{}}},function(e,t,n){var r=n(16);e.exports=function(e,t){if(!r(e))return e;var n,a;if(t&&"function"==typeof(n=e.toString)&&!r(a=n.call(e)))return a;if("function"==typeof(n=e.valueOf)&&!r(a=n.call(e)))return a;if(!t&&"function"==typeof(n=e.toString)&&!r(a=n.call(e)))return a;throw TypeError("Can't convert object to primitive value")}},function(e,t){e.exports=function(e,t){return{enumerable:!(1&e),configurable:!(2&e),writable:!(4&e),value:t}}},,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,function(e,t,n){"use strict";function r(e){return e&&e.__esModule?e:{"default":e}}var a=n(55),o=r(a),i=n(56),u=r(i),s=n(57),c=r(s);$(function(){u["default"].plugin(".js-fee-form"),o["default"].plugin(".js-card-form"),c["default"].plugin(".js-support-table")})},function(e,t,n){"use strict";function r(e){return e&&e.__esModule?e:{"default":e}}Object.defineProperty(t,"__esModule",{value:!0});var a=n(3),o=r(a),i=n(4),u=r(i),s=function(){function e(t){(0,o["default"])(this,e);var n=this;n.$root=$(t),n.locals=n._getDom(),n.apikey=n.$root.data("apikey"),n._getStripeScript().done(function(){Stripe.setPublishableKey(n.apikey),n._init(),n._assignEvents()})}return(0,u["default"])(e,[{key:"_getStripeScript",value:function(){return $.ajax({url:"https://js.stripe.com/v2/",dataType:"script"})}},{key:"_getDom",value:function(){var e=this.$root;return{$inputNumber:e.find("[data-card-number]"),$inputName:e.find("[data-card-name]"),$inputMonth:e.find("[data-card-month]"),$inputYear:e.find("[data-card-year]"),$inputCVC:e.find("[data-card-cvc]"),$submit:e.find("[data-card-submit]")}}},{key:"_init",value:function(){return $.fn.payment?(this.locals.$inputNumber.payment("formatCardNumber"),void this.locals.$inputCVC.payment("formatCardCVC")):void console.log("There is no payment plugin on this page")}},{key:"_assignEvents",value:function(){var e=this;e.$root.on("keyup","input",function(){e._removeError($(this))}).on("change paste keyup","[data-card-name]",function(e){var t=$(this);t.val(t.val().toUpperCase())}).on("submit",e._onSubmitHandler.bind(e))}},{key:"_setError",value:function(e){e.parent().addClass("has-error")}},{key:"_removeError",value:function(e){e.parent().removeClass("has-error")}},{key:"_disabledForm",value:function(){this.locals.$submit.prop("disabled",!0),$("body").css("cursor","progress")}},{key:"_enabledForm",value:function(){this.locals.$submit.prop("disabled",!1),$("body").css("cursor","default")}},{key:"_addTokenInput",value:function(e){var t=this.$root,n='<input type="hidden" value="'+e+'" name="token" />';t.find('input[name="token"]').remove(),t.append(n)}},{key:"_onSubmitHandler",value:function(e){var t=this;e.preventDefault(),this.isValidForm()&&(this._disabledForm(),Stripe.card.createToken(t.$root,t._stripeHandler.bind(t)))}},{key:"_stripeHandler",value:function(e,t){var n=this,r=void 0,a=void 0;t.error?n._enabledForm():(n._addTokenInput(t.id),r=this.$root.serialize(),n._sendFormData(r).done(function(e){if(e.hasOwnProperty("redirect"))window.location=e.redirect;else if(e.hasOwnProperty("message"))success(e.message);else{var t="Internal error #2001. Your card has been charged. ";t+="Do not make payment again. Please proceed to your profile directly.",error(t)}}).fail(function(e,t){"error"==t?(a=JSON.parse(e.responseText),error(a.message)):error("Internal error #2000. Your card has not been charged. Please try again.")}).complete(function(){n._enabledForm()}))}},{key:"_sendFormData",value:function(e){return $.ajax({type:"POST",url:this.$root.attr("action"),data:e,dataType:"json"})}},{key:"isValidForm",value:function(){var e=this,t=!0,n=this.locals,r=$.payment.validateCardNumber(n.$inputNumber.val()),a=$.payment.validateCardExpiry(n.$inputMonth.val(),n.$inputYear.val()),o=$.payment.validateCardCVC(n.$inputCVC.val()),i=+n.$inputName.val().length;return r||(e._setError(n.$inputNumber),t=!1),a||(e._setError(n.$inputMonth),e._setError(n.$inputYear),t=!1),o||(e._setError(n.$inputCVC),t=!1),i||(e._setError(n.$inputName),t=!1),t}}],[{key:"plugin",value:function(t,n){var r=$(t);if(r.length)return r.each(function(t,r){var a=$(r),o=a.data("widget.scrollto");o||(o=new e(r,n),a.data("widget",o))})}}]),e}();t["default"]=s},function(e,t,n){"use strict";function r(e){return e&&e.__esModule?e:{"default":e}}Object.defineProperty(t,"__esModule",{value:!0});var a=n(3),o=r(a),i=n(4),u=r(i),s=function(){function e(t){(0,o["default"])(this,e),this.$root=$(t),this.locals=this._getDom(),this._assignEvents(),this._updateAmount(this.locals.$inputFee)}return(0,u["default"])(e,[{key:"_getDom",value:function(){var e=this.$root;return{$inputFee:e.find(".b-feestrip__input").first(),$taxPlace:e.find("[data-fee-tax]"),$amountPlace:e.find("[data-fee-amount]"),$payPlace:e.find("[data-fee-pay]")}}},{key:"_assignEvents",value:function(){var e=this;e.$root.on("change paste keyup",".b-feestrip__input",function(t){var n=$(this);e._removeError(n),e._updateAmount(n)}).on("submit",e._onSubmitForm.bind(e))}},{key:"_onSubmitForm",value:function(e){if(e.preventDefault(),this.isValidForm()){var t=this.locals.$inputFee.val();this._sendFeeData(t).done(function(e){e.hasOwnProperty("message")&&success(e.message)}).fail(function(e){var t=JSON.parse(jqXHR.responseText);error(t.message)})}}},{key:"_setError",value:function(e){var t=e.parent();t.hasClass("has-error")||t.addClass("has-error")}},{key:"_removeError",value:function(e){e.parent().removeClass("has-error")}},{key:"_updateAmount",value:function(e){var t=this.locals,n=e.val()<1?0:parseInt(e.val()),r=parseFloat(e.data("tax")),a=void 0,o=void 0;a=n*r/100,o=n+a,t.$amountPlace.text(n),t.$taxPlace.text(a),t.$payPlace.text(o)}},{key:"isValidForm",value:function(){var e=!0,t=this.locals.$inputFee,n=t.val().length>0&&!isNaN(t.val());return n||(e=!1,this._setError(t)),e}},{key:"_sendFeeData",value:function(e){return $.ajax({type:"POST",url:this.$root.attr("action"),data:{fee:e},dataType:"json"})}}],[{key:"plugin",value:function(t,n){var r=$(t);if(r.length)return r.each(function(t,r){var a=$(r),o=a.data("widget.scrollto");o||(o=new e(r,n),a.data("widget",o))})}}]),e}();t["default"]=s},function(e,t,n){"use strict";function r(e){return e&&e.__esModule?e:{"default":e}}Object.defineProperty(t,"__esModule",{value:!0});var a=n(3),o=r(a),i=n(4),u=r(i),s=function(){function e(t){(0,o["default"])(this,e),this.$root=$(t),this._assignEvents()}return(0,u["default"])(e,[{key:"_assignEvents",value:function(){var e=this;e.$root.on("click",".dlg-hmfees__link ",function(t){var n=$(this);t.preventDefault(),n.hasClass("state_active")||e._switchTab(n)})}},{key:"_switchTab",value:function(e){var t=this._getTarget(e);t.length&&(t.show().siblings(".table").hide(),e.addClass("state_active").siblings(".dlg-hmfees__link").removeClass("state_active"))}},{key:"_getTarget",value:function(e){return this.$root.find(e.attr("href"))}}],[{key:"plugin",value:function(t,n){var r=$(t);if(r.length)return r.each(function(t,r){var a=$(r),o=a.data("widget.scrollto");o||(o=new e(r,n),a.data("widget",o))})}}]),e}();t["default"]=s}]);