# KmpTemplate

আপনার **Kotlin Multiplatform (KMP)** উন্নয়ন এই GitHub রিপোজিটরি দিয়ে শুরু করুন। [হৃদয় চন্দ্র দাস](https://github.com/ihridoydas) এর ডিজাইন করা এই প্রজেক্টটি Android, iOS, Desktop, এবং Web (Wasm) অ্যাপ্লিকেশনের জন্য একটি শক্তিশালী ভিত্তি প্রদান করে।

## কেন KmpTemplate?

- **ডিফল্টভাবে মাল্টিপ্ল্যাটফর্ম:** Android, iOS, Desktop এবং Web-এর জন্য শেয়ার্ড লজিক এবং UI (Compose Multiplatform)।
- **স্মার্ট সেটআপ:** আপনার প্রজেক্টের নাম, প্যাকেজ আইডি পরিবর্তন করার জন্য এবং প্রয়োজনীয় KMP লাইব্রেরিগুলো কয়েক সেকেন্ডে যুক্ত বা সরানোর জন্য একটি কাস্টম স্ক্রিপ্ট।

## কিভাবে শুরু করবেন?

১. আপনার অ্যাকাউন্টে একটি রিপোজিটরি তৈরি করতে **"Use this template"** ক্লিক করুন।
২. `buildscripts/setup.gradle` ফাইলটি খুলুন এবং আপনার প্রজেক্টের তথ্য কনফিগার করুন:
    ```groovy
    def renameConfig = [
        newTemplateName          : "MyAwesomeApp",    // আপনার প্রজেক্টের নাম
        newTemplateAppId         : "com.example.app", // আপনার প্যাকেজ আইডি
        newMaterialThemeName     : "AppTheme",        // আপনার মেটেরিয়াল থিমের নাম

        // KMP লাইব্রেরি টগল করুন
        useKoin                  : true,
        useKtor                  : true,
        useRoomKmp               : true,
        useComposeMultiplatform  : true,
    ]
    ```
৩. আপনার টার্মিনালে সেটআপ কমান্ডটি রান করুন:
    ```bash
    ./gradlew renameTemplate
    ```
৪. **Android Studio রিস্টার্ট করুন**, গ্রেডল সিঙ্ক করুন এবং আপনি বিল্ড করার জন্য প্রস্তুত!

## কী অন্তর্ভুক্ত আছে

শেয়ার্ড লজিক, কম্পোনেন্ট এবং ডকুমেন্টেশন অন্বেষণ করুন:

- [Essential KMP Tasks](/documentation/EssentialTasks.md) - প্ল্যাটফর্ম-নির্দিষ্ট কমান্ডের জন্য **এখান থেকে শুরু করুন**।
- [Ktlint](/documentation/StaticAnalysis.md) কোড ফরম্যাটিং এর জন্য।
- [Detekt](/documentation/StaticAnalysis.md) কোড স্মেলস এর জন্য।
- [Git Hooks](/documentation/GitHooks.md) প্রি-কমিট চেক এর জন্য।
- [GitHub Actions](/documentation/GitHubActions.md) CI/CD এর জন্য।
- [Dokka](/documentation/StaticAnalysis.md) API ডকুমেন্টেশনের জন্য।

## প্রজেক্ট স্ট্রাকচার

- `:app`: Android-নির্দিষ্ট অ্যাপ্লিকেশন মডিউল।
- `:common`: প্রজেক্টের মূল অংশ। এখানে শেয়ার্ড UI (Compose) এবং বিজনেস লজিক থাকে।
- `:navigation`: শেয়ার্ড নেভিগেশন কনফিগারেশন।
- `:storage`: শেয়ার্ড লোকাল ডাটা হ্যান্ডলিং (DataStore/Room)।
- `:theme`: শেয়ার্ড Material 3 ডিজাইন সিস্টেম।
