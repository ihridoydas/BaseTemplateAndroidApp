# KmpTemplate

अपने **Kotlin Multiplatform (KMP)** विकास की शुरुआत इस GitHub रिपॉजिटरी के साथ करें। [हृदय चंद्र दास](https://github.com/ihridoydas) द्वारा डिज़ाइन किया गया, यह Android, iOS, Desktop और Web (Wasm) अनुप्रयोगों के लिए एक मजबूत आधार प्रदान करता है।

## KmpTemplate क्यों?

- **डिफ़ॉल्ट रूप से मल्टीप्लेटफ़ॉर्म:** Android, iOS, Desktop और Web के लिए साझा लॉजिक और UI (Compose Multiplatform)।
- **स्मार्ट सेटअप:** अपने प्रोजेक्ट का नाम, पैकेज आईडी बदलने और आवश्यक KMP लाइब्रेरीज़ को कुछ ही सेकंड में टॉगल करने के लिए एक कस्टम स्क्रिप्ट।

## शुरुआत कैसे करें

1. अपने खाते में एक रिपॉजिटरी बनाने के लिए **"Use this template"** पर क्लिक करें।
2. `buildscripts/setup.gradle` खोलें और अपने प्रोजेक्ट का विवरण कॉन्फ़िगर करें:
    ```groovy
    def renameConfig = [
        newTemplateName          : "MyAwesomeApp",    // आपका प्रोजेक्ट नाम
        newTemplateAppId         : "com.example.app", // आपकी पैकेज आईडी
        newMaterialThemeName     : "AppTheme",        // आपका मटेरियल थीम नाम

        // KMP लाइब्रेरीज़ टॉगल करें
        useKoin                  : true,
        useKtor                  : true,
        useRoomKmp               : true,
        useComposeMultiplatform  : true,
    ]
    ```
3. अपने टर्मिनल में सेटअप कमांड चलाएँ:
    ```bash
    ./gradlew renameTemplate
    ```
4. **Android Studio को पुनरारंभ करें**, Gradle को सिंक करें, और आप बिल्ड के लिए तैयार हैं!

## क्या शामिल है

साझा लॉजिक, कंपोनेंट्स और दस्तावेज़ों का अन्वेषण करें:

- [Essential KMP Tasks](/documentation/EssentialTasks.md) - प्लेटफॉर्म-विशिष्ट कमांड के लिए **यहाँ से शुरू करें**।
- [Ktlint](/documentation/StaticAnalysis.md) कोड स्वरूपण के लिए।
- [Detekt](/documentation/StaticAnalysis.md) कोड स्मेल्स के लिए।
- [Git Hooks](/documentation/GitHooks.md) प्री-कमिट चेक के लिए।
- [GitHub Actions](/documentation/GitHubActions.md) CI/CD के लिए।
- [Dokka](/documentation/StaticAnalysis.md) API दस्तावेज़ीकरण के लिए।

## प्रोजेक्ट संरचना

- `:app`: Android-विशिष्ट एप्लिकेशन मॉड्यूल।
- `:common`: आपके प्रोजेक्ट का हृदय। साझा UI (Compose) और व्यावसायिक लॉजिक शामिल है।
- `:navigation`: साझा नेविगेशन कॉन्फ़िगरेशन।
- `:storage`: साझा स्थानीय डेटा हैंडलिंग (DataStore/Room)।
- `:theme`: साझा Material 3 डिज़ाइन सिस्टम।
