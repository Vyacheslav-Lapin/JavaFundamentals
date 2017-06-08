import com.epam.training.jf.reflect.MyClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class BaseReflectionTest {

    @Test
    @DisplayName("Three way of class getting are equals")
    void threeWayOfClassGettingAreEquals() throws ClassNotFoundException {

        Class<MyClass> myClass = MyClass.class;

        MyClass myObject = new MyClass();
        Class<? extends MyClass> myClass2 = myObject.getClass();

        assertThat(myClass2, is(myClass));

        // It`s possible to load a class by its full name:
        String className = "com.epam.training.jf.reflect.MyClass";
        Class<?> myClass3 = Class.forName(className);

        assertThat(myClass3, is(myClass));
    }

    @Test
    @DisplayName("getInterfaces() method works correctly")
    void getInterfacesWorksCorrectly() {
        Class[] interfaces = MyClass.class.getInterfaces();
        Arrays.stream(interfaces)
                .map(Class::isInterface)
                .forEach(Assertions::assertTrue);
    }
}
