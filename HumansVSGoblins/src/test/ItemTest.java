import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class ItemTest {

    @BeforeEach
    void setUp(){

    }

    @Test
    void goblinLoot(){
        Item item = new Item('g');

        if(item.getName().contentEquals("Club")||item.getName().contentEquals("Spear")||item.getName().contentEquals("Sword")){
            assertEquals(item.getType(),1,"Did not create a proper item");
        }else if(item.getName().contentEquals("Pork Chop")||item.getName().contentEquals("Steak")||item.getName().contentEquals("Unidentifiable Meat")){
            assertEquals(item.getType(),1,"Did not create a proper item");
        }
    }

}