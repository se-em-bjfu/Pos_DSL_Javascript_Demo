//不打折
var total=sale.preDiscountTotal;

//打八折
var total=sale.preDiscountTotal*0.8;

//满200减20
var total=sale.preDiscountTotal-Math.floor(sale.preDiscountTotal/200)*20;