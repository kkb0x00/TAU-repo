function isLengthValid(string){
    return string.length < 15 && string.length > 0;
};

function isLettersOnly(string){
    return string.match(/^[a-z]+$/i);
};

export function formmaster(selector) {
    return {
        validate (selector) {
            let element = document.querySelector(selector);
    
            if(!isLengthValid(element.value)) {
                element.classList.add("invalid");
                
                return;
            }
    
            if(!isLettersOnly(element.value)) {
                element.classList.add("invalid");

                return;
            }

            element.classList.remove("invalid");
         }
    }
 }; 

//  export default formaster;