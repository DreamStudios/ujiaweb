!function(o){o.fn.apphover=function(t){var e,a={offsetTarget:"body"};return e=t&&t.offsetTarget?t.offsetTarget:a.offsetTarget,this.on("mouseenter","li",function(){var t=o(this);t.addClass("active"),t.find(".quickdown").animate({top:"0"},150,function(){o(this).show()})}).on("mouseleave","li",function(){var t=o(this);t.removeClass("active"),t.find(".quickdown").css("top","28px")}).on("mousemove","li",function(t){var a=o(this),i=a.find(".app-popover"),n=t.pageX,s=t.pageY,f=s-23-160,r=n+28;if(a.hasClass("last")&&(r=n-28-259),"body"!==e){var p=o(e).position();f-=p.top,r-=p.left,f=0>f?2:f}440>s&&(f=Math.max(f,180)),i.css({top:f,left:r})})}}(window.jQuery);
$(function(){var e=!!navigator.userAgent.match(/msie [6]/i);e&&$(".rec-mixed-item").hover(function(){$(this).toggleClass("rec-mixed-item-hover")});var a=null;Mobres.isWebsuite&&(Mobres.websuite_qrcode_callback=function(){if(a){var e=a.attr("data-docid");if(e){var s=Mobres.websuite.qrcode_generate({docid:e});-1===s.indexOf("undefined")&&a.attr("src",s).addClass("app-qrcode-ws")}}}),$(".app-bd").on("mouseenter","li",function(){var e=$(this);if(Mobres.isWebsuite){var s=e.find(".wsqrcode"),i=s.attr("data-docid");if(i){var t=Mobres.websuite.qrcode_generate({docid:i});a=s,-1===t.indexOf("undefined")&&s.attr("src",t).addClass("app-qrcode-ws")}}}),$(".app-bd").apphover(),$(".board-app .hd li").on("mouseover",function(){var e=this;$(this).closest(".hd").find("li").removeClass("cur"),$(this).addClass("cur"),$(this).parent().parent().parent().find(".bd .app-wrap").each(function(a,s){a+=1,$(s).removeClass("cur"),$(e).data("index")===a&&$(s).addClass("cur")})}),$(".sec-soft .change-page, .sec-game .change-page").on("click",function(){var e=$(this).parent().parent(),a=e.find(".app-bd");if(!(a.length<2)){var s=e.find(".show"),i=s.next().length?s.next():a.eq(0);s.removeClass("show").addClass("hide"),i.removeClass("hide").addClass("show")}}),$(".sec-soft-hot .pagewp a, .sec-game-hot .pagewp a").on("click",function(){var e=$(this).parent();e.find(".page").removeClass("active"),$(this).addClass("active");var a=parseInt($(this).html(),10),s=$(this).parent().parent().parent(),i=s.find(".app-bd");i.removeClass("show").addClass("hide"),i.eq(a-1).removeClass("hide").addClass("show")}),setTimeout(function(){Mobres.imageFix()},100),function(){var e=$(".slide-list-wrap"),a=e.find("li"),s=[],i=(a.length,$(".slide-prev")),t=$(".slide-next");a.each(function(e,a){s.push(a)}),i.bind("click",function(){s.unshift(s.pop()),e.prepend(s[0]),e.css("left","-224px"),e.animate({left:"+=224"})}),t.bind("click",function(){e.animate({left:"-=224"},function(){e.append(s[0]),s.push(s.shift()),e.css("left","0")})})}()});