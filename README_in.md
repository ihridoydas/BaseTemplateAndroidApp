# बेस टेम्पलेट Android एप्लिकेशन

इस GitHub टेम्पलेट रिपॉजिटरी के साथ अपनी Android एप्लिकेशन विकास की शुरुआत करें। [हृदय चंद्र दास](https://github.com/ihridoydas) द्वारा डिज़ाइन किया गया है, यह कोड लेखन के राय-निर्देश ना देते हुए महत्वपूर्ण टूल्स प्रदान करता है।

## इस टेम्पलेट का कारण?

- **चयन की स्वतंत्रता:** कोड संरचना या आर्किटेक्चर पर कोई राय नहीं है। डेवेलपर्स खुद चुनते हैं।
- **राय निर्देशित टूलिंग:** कॉन्फ़िगर किए गए डिपेंडेंसी प्रबंधन, git हुक्स, कोड स्वरूपिता, और स्थायी विश्लेषण के लिए।

[AndroidAppTemplate](https://github.com/AdamMc331/AndroidAppTemplate) से प्रेरित।

## शुरुआत कैसे करें

1. अपने खाते में एक रिपॉजिटरी बनाने के लिए "इस टेम्पलेट का उपयोग करें" पर क्लिक करें।
    ```dsl
    templateName             : "template",
    templateAppId            : "template.app.id",
    templateMaterialThemeName: "TemplateTheme",
    newTemplateName          : "Project", [यहां अपना प्रोजेक्ट नाम दर्ज करें]
    newTemplateAppId         : "domain.yourname.app", [यहां अपना प्रोजेक्ट पैकेज नाम दर्ज करें]
    newMaterialThemeName     : "MyMaterialTheme", [यहां अपने प्रोजेक्ट थीम नाम दर्ज करें]
    useHiltDependencies      : true,
    useRoomDependencies      : true,
    useRetrofitDependencies  : true,
    usePaparazziDependencies : true,
    ```
2. [setup.gradle](buildscripts/setup.gradle) को समायोजित करके और `./gradlew renameAllModules` चलाकर कस्टमाइज़ करें।

## क्या शामिल है

[/documentation](/documentation) में तीसरी पक्ष की डिपेंडेंसीज़ और दस्तावेज़ का अन्वेषण करें। कुछ महत्वपूर्ण शामिल हैं:

- [केटलिंट](/documentation/StaticAnalysis.md) के लिए कोड स्वरूपिता।
- [डिटेक्ट](/documentation/StaticAnalysis.md) के लिए कोड स्मेल्स।
- [गिट हुक्स](/documentation/GitHooks.md) स्थायी विश्लेषण की जाँच के लिए।
- [GitHub Actions](/documentation/GitHubActions.md) लगातार समृद्धि के लिए।
- [लीक कैनरी](https://square.github.io/leakcanary/) मेमोरी लीक की पहचान के लिए।
- [हिल्ट](https://developer.android.com/training/dependency-injection/hilt-android) और [रूम](https://developer.android.com/training/data-storage/room) डिपेंडेंसीज़ (setup.gradle के माध्यम से हटा जा सकता है)।
- [पापाराज़ी](https://github.com/cashapp/paparazzi) डिपेंडेंसी (setup.gradle के माध्यम से हटा जा सकता है)।
- [Dokka](https://github.com/Kotlin/dokka) डिपेंडेंसी, जो सभी प्रोजेक्ट और मॉड्यूल को डॉक्यूमेंट करता है।
- [Spotless](https://github.com/diffplug/spotless) डिपेंडेंसी, जो आपके कोड को स्पॉटलेस रखता है।
- [sortDependencies](https://github.com/square/gradle-dependencies-sorter) डिपेंडेंसी, जो build.gradle फ़ाइल में डिपेंडेंसिज़ को साज़ा करता है।

## डिपेंडेंसी सेटअप

डिपेंडेंसीज़ [/buildscripts](/buildscripts) में संरचित हैं। ऐप मॉड्यूल डिपेंडेंसीज़ को [libs.versions.toml](gradle/libs.versions.toml) में एक Gradle संस्करण सूची का उपयोग करके परिभाषित किया गया है।

## डेंजर चेक्स

PR जाँच के लिए [डेंजर](https://danger.systems) का उपयोग करता है। [Dangerfile](Dangerfile) देखें। GitHub Actions के लिए GitHub सीक्रेट्स में Danger API कुंजी सेट करें।

## टेम्पलेट्स

व्यवस्थित PR विवरण के लिए [पुल रिक्वेस्ट टेम्पलेट](/.github/pull_request_template.md) शामिल है।
