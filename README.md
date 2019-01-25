Step 1.依赖
在Project的build.gradle中
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  在Module的build.gradle中
implementation 'com.github.13752991610:MyRetrofit:v1.0'
