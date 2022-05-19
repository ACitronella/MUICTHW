# ITCS231-Project2


## Run
```
java -jar ./target/App.jar <cities...> [-p <path to the dataset>] [-f]
```
or
```
java src.App <cites...> [-p <path to the dataset>] [-f]
```
both are equivalent

Whereas
-    -p \<path\>     explicitly specify dataset's path 
-    -f              print all infomation about inputed cities


### Example
```
java -jar ./target/App.jar "Phasi Charoen"
```
```
java src.App "Phasi Charoen" "Bang Su" "Bang Len" -p "I:\OneDrive - Mahidol University\ITCS231 Data Structure and Algorithm Analysis\project2\TH.txt" -f
```

## Build
```
javac ./src/*.java
jar cfe ./target/App.jar src.App ./src/App.class ./src/GeoName.class
```

## Java version used
- Openjdk 16.0.1
