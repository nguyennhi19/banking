function calculatorFee(){
    let salary = parseFloat(document.getElementById("salary").value);
    if(isNaN(salary)){
        document.getElementById("amount").value = 0;
    }
    else {
        let feeAmount = salary * 5/100;
        document.getElementById("amount").valueOf = feeAmount;
    }
}