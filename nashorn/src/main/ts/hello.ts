interface Java {
    type(s:String):
}

const JavaClass = Java.type("nashorn.JavaClass");
const javaInstance = new JavaClass;

const getHelloMessage = owner => {
    return `Hello, from ${javaInstance.getName()}, called from `;
};