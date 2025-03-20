import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class ShoppingCartTest {
    @Test
    public void testShoppingCart() {
        assertEquals(10.0,ShoppingCart.itemCalculator(2,5.0f),0.001);
    }
}
