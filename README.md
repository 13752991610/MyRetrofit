## 使用步骤
#### Step 1.依赖
Gradle 
```groovy
在Project的build.gradle中加入
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }	
		}
	}
```
```groovy
在Module的build.gradle中
implementation 'com.github.13752991610:MyRetrofit:v1.0'
```


	
  
