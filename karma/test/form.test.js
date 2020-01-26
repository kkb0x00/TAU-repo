import {formmaster} from '../js/formMaster.js';


describe("Name input validation tests", function () {
    let formMaster = formmaster();

    beforeEach(function () {
        let input = document.createElement('input');
        input.setAttribute("id", "input");
        input.setAttribute("type", "text");
        document.body.appendChild(input)
    });

    afterEach(function () {
        $('#input').remove();
    });

    it("should pass validators", function () {
        let input = document.getElementById("input");
        input.value = "Krzysiek"

        formMaster.validate("#input");
        expect(input.className).toBeEmpty;
    });

    it("should not pass length validator", function () {
        let input = document.getElementById("input");
        input.value = "Krzysieksdfsdfdsfsdfdsfs"

        formMaster.validate("#input");
        expect(input.className).toBe("invalid");
    });

    it("should not pass alphanumeric validator", function () {
        let input = document.getElementById("input");
        input.value = "$53"

        formMaster.validate("#input");
        expect(input.className).toBe("invalid");
    });

    it("should not pass all validators", function () {
        let input = document.getElementById("input");
        input.value = "Tsdfsdfsdddfsdf^35*"

        formMaster.validate("#input");
        expect(input.className).toBe("invalid");
    });
});
