interface JavaClass {
    new(s:any)
}

interface JavaType {
    type<T extends JavaClass>(s:String): T
}

declare let Java: JavaType;

const JavaClass = Java.type("nashorn.JavaClass");
const javaInstance = new JavaClass(function() {''});

const getHelloMessage = owner => {
    return `Hello from Nashorn, ${javaInstance.getName()}!`;
};