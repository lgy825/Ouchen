//jsRender公用转换函数
//调用方法为:{{statusToString:status}} 
//statusToString为转换函数名称
//status为要转换的状态码

Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    }
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}

$.views.converters({
    date: function (val) {
        if (val) {
            var ret;
            var value = new Date(val);
            ret = value.getFullYear() + "-" + (value.getMonth() + 1) + "-" + value.getDate();
            return ret;
        }
    },
    dateDot: function (val) {
        if (val) {
            var ret;
            var value = new Date(val);
            ret = value.getFullYear() + "." + (value.getMonth() + 1) + "." + value.getDate();
            return ret;
        }
    },
    dateSplit: function (val) {
        if (val) {
            var ret;
            ret = val.split(" ");
            return ret[0];
        }
    },
    formatCurency: function (num) {
        if (!num) {
            num = 0;
        }
        return (parseFloat(num) / 100).toFixed(2);
    },
    formatNumNull0: function (num) {//null 转为0
        if (!num) {
            num = 0;
        }
        return num;
    },
    formatDoubleCurency: function (num) {
        if (!num) {
            num = 0;
        }
        return (parseFloat(num)).toFixed(2);
    },
    standardHover: function (isDefault) {
        if (isDefault && isDefault == true) {
            return 'class="hover"'
        }
    },
    dateTime: function (val) {
        if (val) {
            val = new Date(val);
            var pattern = "yyyy-MM-dd hh:mm:ss";
            return val.format(pattern);
        }
    },
    dateTimeWithBR: function (val) {
        if (val) {
            val = new Date(val);
            var pattern = "yyyy-MM-dd hh:mm:ss";
            return val.format(pattern).replace(" ", "<br/>");
        }
    },
    date: function (val) {
        if (val) {
            val = new Date(val);
            var pattern = "yyyy-MM-dd";
            return val.format(pattern);
        }
    },

    ststusWithdraw: function (num) {
        var ret;
        if (1 == num) {
            ret = '提现中';
        } else if (3 == num) {
            ret = '提现失败';
        } else if (5 == num) {
            ret = '已到账';
        }
        return ret;
    },

    statusAmount: function (num1, num2) {
        var ret;
        if (1 == num2) {
            ret = num1;
        } else {
            ret = "";
        }
        return ret;
    },
    statusAmount1: function (num1, num2) {
        var ret;
        if (2 == num2) {
            ret = num1;
        } else {
            ret = "";
        }
        return ret;
    },
    nullToZero: function (val) {
        if (val) {
            if (val == 'null')
                return 0;
            else
                return val;
        } else {
            return 0;
        }
    },
    //获取要合并的行数
    isShow: function (data) {
        var val = 0;
        var list = data.orderItemList;
        var size = list.length;
        for (var i = 0; i < size; i++) {
            if (list[i].promotionType == '3') {
                val += 2;
            } else {
                val += 1;
            }
        }
        return val;
    },
    isShoww1: function (data) {
        return data.itemList.length;
    },
    packetType: function (num) {
        var ret;
        if (1 == num) {
            ret = '票务定向红包';
        } else if (2 == num) {
            ret = '购票赠送红包';
        } else if (3 == num) {
            ret = '限量领取红包';
        } else if (4 == num) {
            ret = '附近商家红包';
        } else if (5 == num) {
            ret = '卖品定向红包';
        } else if (6 == num) {
            ret = '购物赠送红包';
        }
        return ret;
    },
    splitString:function (str) {
        if(str && str.length>20){
            return str.substring(0,20)+"...";
        }
        return str;
    },
    packetStatus: function (num) {
        var ret;
        if (0 == num) {
            ret = '未开始';
        } else if (1 == num) {
            ret = '进行中';
        } else if (2 == num) {
            ret = '已停止';
        } else if (3 == num) {
            ret = '已过期';
        }
        return ret;
    },
   formatDecimal:function (val) {
       var x = val || 0;//val
       var f = parseFloat(x);
       if (isNaN(f)) {
           var f = 0.00;
       }
       var f = Math.round(x * 100) / 100;
       var s = f.toString();
       var rs = s.indexOf('.');
       if (rs < 0) {
           rs = s.length;
           s += '.';
       }
       while (s.length <= rs + 2) {
           s += '0';
       }
       return s;
   },
    dateTimeToMouthAndDay:function (val) {
        if(!val)
            return "";
        var split=val.split(" ");
        var dateStr=split[0];
        var timeStr=split[1];
        var fullYear=dateStr.split("-")[0];
        var fullMouth=dateStr.split("-")[1];
        var fullDay=dateStr.split("-")[2];
        return fullMouth+"."+fullDay;
    }
});

