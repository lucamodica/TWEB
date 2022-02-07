
function fact(n) {
    let fact = 1;
    do {
        fact *= n;
        n--;
    }while (n > 1);
    return fact;
}

function func(n1, n2) {
    //Type check
    if (typeof n1 != 'number' ||
        typeof n2 != 'number') {
        throw {
            name: 'TypeError',
            message: 'Type mismatch error'
        }
    }
    //Positivity check
    if (n1 < 0 || n2 < 0){
        throw {
            name: 'IntError',
            message: 'Number Negative Error'
        }
    }


    return {
        factorial: fact(n1),
        product: n1 * n2
    }
}