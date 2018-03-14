package demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @RequestMapping("/test")
    public PurchaseOrderType getPurchaseOrder() {
        return new PurchaseOrderType(3456);
    }
}
