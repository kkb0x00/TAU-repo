function isLengthValid(string){
    return string.length < 15 && string.length > 0;
};

function isAsciiOnly(string){
    return string.match(/^[a-z0-9]+$/i);
};

export function formmaster(selector) {
    return {
        validate (selector) {
            let element = document.querySelector(selector);
    
            if(!isLengthValid(element.value)) {
                element.classList.add("invalid");
                
                return;
            }
    
            if(!isAsciiOnly(element.value)) {
                element.classList.add("invalid");

                return;
            }

            element.classList.remove("invalid");
         }
    }
 }; 

//  export default formaster;