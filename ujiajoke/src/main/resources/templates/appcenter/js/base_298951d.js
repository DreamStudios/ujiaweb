!function(e,t){function r(e){var t=e.indexOf("//"),r=e.indexOf("?"),n={};if(-1===t)return!1;n.protocol=e.slice(0,t-1);var a=-1===r?e.slice(t+2):e.slice(t+2,r),o=-1===r?"":e.slice(r+1),i=a.indexOf(":"),s=a.indexOf("/");-1===s?(n.path="",n.host=-1===i?a:a.slice(0,i),n.port=-1===i?80:parseInt(a.slice(i),10)):(n.path=a.slice(s+1),n.host=a.slice(0,-1===i?s:i),n.port=-1===i?80:parseInt(a.slice(i+1,s),10)),n.original_url=e,n.query={};var c=o.split("&");query_params_len=c.length;for(var l=0;query_params_len>l;l++){var u=c[l].indexOf("=");-1!==u&&(n.query[decodeURIComponent(c[l].slice(0,u))]=decodeURIComponent(c[l].slice(u+1)))}return n}function n(e,t){for(var r=0;r<e.length;++r)if(e[r]!==t[r])return e[r]>t[r]?!0:!1;return e.length===t.length}function a(e){for(var t=0;e>1024;)e/=1024,t++;switch(t){case 0:return e+"B";case 1:return e.toFixed(2)+"KB";case 2:return e.toFixed(2)+"MB";case 3:return e.toFixed(2)+"GB";case 4:return e.toFixed(2)+"TB";default:return""}}function o(e){var t="";return e=e||0,50>e?t=""+e:e>50&&100>e?t="50+":e>100&&500>e?t="100+":e>500&&1e3>e?t="500+":e>1e3&&5e3>e?t="1千+":e>5e3&&1e4>e?t="5千+":e>1e4&&5e4>e?t="1万+":e>5e4&&1e5>e?t="5万+":e>1e5&&5e5>e?t="10万+":e>5e5&&1e6>e?t="50万+":e>1e6&&5e6>e?t="100万+":e>5e6&&1e7>e?t="500万+":e>1e7&&5e7>e?t="1000万+":e>5e7&&1e8>e?t="5000万+":e>1e8&&5e8>e?t="1亿+":e>5e8&&1e9>e?t="5亿+":e>1e9&&(t="10亿+"),t}function i(e){var t="";return 60>e?t=e+"秒":3600>e?t=parseInt(e/60,10)+"分"+e%60+"秒":216e3>e&&(hour=parseInt(e/3600,10),min=parseInt((e-60*hour)/60,10),secs=e-60*hour*60-60*min,t=hour+"时"+min+"分"+secs+"秒"),t}function s(e,r){var n="/mobres/log?beacon=1",a="";for(var o in e)e.propertyIsEnumerable(o)&&(a+="&"+o+"="+encodeURIComponent(e[o]));var i=t.location.search.match(/from=[^&]*/)||document.cookie.match(/from=[^;]*/);i&&(a+="&"+i[0]);var s="__logImg_"+ +new Date,l=t[s]=new Image;l.onload=l.onerror=function(){t[s]=null},l.src=n+a+"&_t="+(void 0===typeof r?+new Date:c(r)),l=null}function c(e){for(var t=3,r=2,n=3,a=n>=e?[1,3,4,7,8]:[0,2,5,6,9],o=a.length,i=""+(new Date).getTime(),s=parseInt(i.slice(-1),10),c=s%t,l=i.slice(0,13-t-1),u=i.slice(13-t-1,-1).split("").reverse(),f=0;r>f;f++)u[(c-f+t)%t]=a[parseInt(o*Math.random(),10)];return l+u.reverse().join("")+s}function l(e,t,r,n,a,o,i){var c={};if(e){c.type=window.Mobres.isWebsuite?"ws_"+(o?"set"+o:"install"):"install",t&&(c.tj=t);var l=e;l+="@"+(r?r:""),l+="@"+(n?n:""),a&&(l+="@"+a),c.f=l,s(c,i)}}var u={byteToStr:a,numToStr:o,secToStr:i,parseUrl:r,versionCompare:n},f={};f.utils=u,f.trackInstall=l,f.linkTrack=s,f.imageFix=function(t){e("body").imageFix(t)},"undefined"==typeof t.Mobres&&(t.Mobres=f),e(function(){e("body").on("click",".inst-btn, .inst-btn-big, apk",function(t){t.preventDefault(),t.stopPropagation();var r=e(t.currentTarget),n=r.data("cn");n=n?++n:1,r.data("cn",n),f.trackInstall(r.attr("data-action"),r.attr("data-tj"),r.attr("data-mod"),r.attr("data-pos"),r.attr("data-pre_f"),r.attr("data-set"),n)})})}(jQuery,window);