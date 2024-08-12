import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { RadioGroup } from "@/components/ui/radio-group";
import { DotFilledIcon } from "@radix-ui/react-icons";
import { RadioGroupItem } from "@radix-ui/react-radio-group";
import React, { useState } from "react";

const TopupForm = () => {
  const [amount, setAmount] = useState("");
  const [paymentMethod, setPaymentMethod] = useState("RAZORPAY");
  const handlePaymentMethodChange = (value) => {
    setPaymentMethod(value);
  };
  const handleChange = (e) => {
    setAmount(e.target.value);
  };
  const handleSubmit = () =>{
    console.log(amount, paymentMethod);
    
  }
  return (
    <div className="pt-10 space-y-5">
      <div>
        <h1 className="pb-1">Enter Amount</h1>
        <Input
          onChange={handleChange}
          value={amount}
          className="py-7 text-lg"
          placeholder="$1000"
        />
      </div>
      <div>
        <h1 className="pb-1">Select payment method</h1>
        <RadioGroup
          onValueChange={(value) => handlePaymentMethodChange(value)}
          className="flex"
          defaultValue="RAZORPAY"
        >
            <div className="flex items-center space-x-2 border p-3 px-5 rounded-md">
                <RadioGroupItem icon={DotFilledIcon} className="h-9 w-9" value="RAZORPAY" id="r1" />
                <Label htmlFor="r1">
                    <div className="bg-white rounded-md px-5 py-2 w-32">
                        <img src="https://seeklogo.com/images/R/razorpay-logo-742FCD4C3C-seeklogo.com.png" alt="" />
                    </div>
                </Label>
            </div>

            <div className="flex items-center space-x-2 border p-3 px-5 rounded-md">
                <RadioGroupItem icon={DotFilledIcon} className="h-9 w-9" value="STRIPE" id="r1" />
                <Label htmlFor="r1">
                    <div className="bg-white rounded-md px-5 w-32">
                        <img className="h-8" src="https://cdn.iconscout.com/icon/free/png-512/free-stripe-logo-icon-download-in-svg-png-gif-file-formats--online-payment-logos-pack-icons-226458.png?f=webp&w=256" alt="" />
                    </div>
                </Label>
            </div>
        </RadioGroup>
      </div>
      <Button onClick={handleSubmit} className="w-full py-7">
        Submit
      </Button>
    </div>
  );
};

export default TopupForm;
