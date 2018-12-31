# SampleGoogleMapsDirection
Sample Google Maps Direction API for Java and Kotlin

![](https://i.ibb.co/dB29qzm/Screenshot-12.png)

Sample api from Google Maps Direction API <br>
https://maps.googleapis.com/maps/api/directions/json?origin=-6.3035467,106.8693513&destination=-6.1890675,106.8251718&key=[YOUR_API_KEY]

Using Google Maps Utils Library
```gradle
implementation 'com.google.maps.android:android-maps-utils:0.5'
```

Get ```overview_polyline``` in JSON result using Retrofit for draw shape route and decode with this line


for java:
```java
PolylineOptions polyline = new PolylineOptions()
        .addAll(PolyUtil.decode(string_shape))
        .width(8f)
        .color(Color.RED);
map.addPolyline(polyline);
```

for kotlin:
```kotlin
val polyline = PolylineOptions()
        .addAll(PolyUtil.decode(string_shape))
        .width(8f)
        .color(Color.RED)
map.addPolyline(polyline)
```

Sample Code<br>
https://github.com/utsmannn/SampleGoogleMapsDirection/tree/master/app/src/main/java/com/utsman/samplegooglemapsdirection

Java<br>
https://github.com/utsmannn/SampleGoogleMapsDirection/blob/master/app/src/main/java/com/utsman/samplegooglemapsdirection/java/MainActivity.java

Kotlin<br>
https://github.com/utsmannn/SampleGoogleMapsDirection/blob/master/app/src/main/java/com/utsman/samplegooglemapsdirection/kotlin/MainActivity.kt
