# বেস টেমপ্লেট Android অ্যাপ

আপনার Android অ্যাপ উন্নত করতে এই GitHub টেমপ্লেট রিপোজিটরি দিয়ে শুরু করুন। [হৃদয় চন্দ্র দাস](https://github.com/ihridoydas) এর ডিজাইন, এটি কোড লেখার মতামত দেওয়া ছাড়া প্রয়োজনীয় সরঞ্জাম সরবরাহ করেছেন ।

## এই টেমপ্লেট ব্যবহার করবেন কেন?

- **চয়নের স্বাধীনতা:** কোড কাঠামো বা আর্কিটেকচার নিয়ে কোনও মতামত নেই। ডেভেলপাররা নিজেদের নির্ধারণ করে।
- **মতামতদাতা টুলিং:** কনফিগার করা ডিপেন্ডেন্সি ম্যানেজমেন্ট, গিট হুক, কোড ফরম্যাটিং এবং স্ট্যাটিক অ্যানালাইসিসের জন্য এটি অপিনিয়ানিটেড টুলিং সরবরাহ করে।

[AndroidAppTemplate](https://github.com/AdamMc331/AndroidAppTemplate)-এর অনুপ্রেরিত।

## কিভাবে শুরু করবেন?

1. আপনার অ্যাকাউন্টে একটি রিপোজিটরি তৈরি করতে "এই টেমপ্লেট ব্যবহার করুন" ক্লিক করুন।
   সেটাপ ফাইল এর নিচের অংশগুলো পরিবর্তন করুন।

    ```dsl
   templateName             : "template",
   templateName             : "template",
   templateAppId            : "template.app.id",
   templateMaterialThemeName: "TemplateTheme",
   newTemplateName          : "Project", [আপনার প্রজেক্ট এর নাম লিখুন এখানে]
   newTemplateAppId         : "domain.yourname.app", [আপনার প্রজেক্ট এর প্যাকেজ নেম লিখুন এখানে]
   newMaterialThemeName     : "MyMaterialTheme", [আপনার প্রজেক্ট এর থিম এর নাম লিখুন এখানে]
   useHiltDependencies      : true,
   useRoomDependencies      : true,
   useRetrofitDependencies  : true,
   usePaparazziDependencies : true,
   
   ```



2. [setup.gradle](buildscripts/setup.gradle) সংশোধন করে `./gradlew renameAllModules` রান করুন ।

## কী অন্তর্ভুক্ত আছে

[/documentation](/documentation) এ তৃতীয়-পক্ষ ডিপেন্ডেন্সি এবং নথিপত্র অন্বেষণ করুন। মন্নত অন্তর্ভুক্ত:

- [Ktlint](/documentation/StaticAnalysis.md) কোড ফরম্যাটিং জন্য।
- [Detekt](/documentation/StaticAnalysis.md) কোড স্মেলস জন্য।
- [Git Hooks](/documentation/GitHooks.md) স্থায়ী অ্যানালাইসিস পরীক্ষার জন্য।
- [GitHub Actions](/documentation/GitHubActions.md) এ অবিরত সংক্রিয়া।
- [LeakCanary](https://square.github.io/leakcanary/) মেমোরি লিক পরিচিতির জন্য।
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) এবং [Room](https://developer.android.com/training/data-storage/room) ডিপেন্ডেন্সি (setup.gradle দ্বারা অপসারণযোগ্য)।
- [Paparazzi](https://github.com/cashapp/paparazzi) ডিপেন্ডেন্সি (setup.gradle দ্বারা অপসারণযোগ্য)।
- [Dokka](https://github.com/Kotlin/dokka) ডিপেন্ডেন্সি, যা সমস্ত প্রকল্প এবং মডিউল ডকুমেন্ট করে।
- [Spotless](https://github.com/diffplug/spotless) ডিপেন্ডেন্সি, যা আপনার কোড স্পটলেস রাখে।
- [sortDependencies](https://github.com/square/gradle-dependencies-sorter) ডিপেন্ডেন্সি, যা বিল্ড.gradle ফাইলে ডিপেন্ডেন্সিগুলি সাজায়।

## ডিপেন্ডেন্সি সেটআপ

ডিপেন্ডেন্সিগুলি [/buildscripts](/buildscripts) -এ স্ট্রাকচারবদ্ধ হয়েছে। [libs.versions.toml](gradle/libs.versions.toml) -এ একটি গ্রেডল সংস্করণ ক্যাটালগে অ্যাপ মডিউলের ডিপেন্ডেন্সি সহ সংজ্ঞানাত্মকভাবে সংজ্ঞানাত্মক করা হয়েছে।

## ডেঞ্জার চেক

PR চেক এর জন্য [ডেঞ্জার](https://danger.systems) ব্যবহার করে। [Dangerfile](Dangerfile) দেখুন। GitHub অ্যাকশন্সের জন্য GitHub সিক্রেটসে Danger API কী সেট করুন।

## টেমপ্লেট PR

বিন্যাসবদ্ধ PR বর্ণনা জন্য [পুল রিকুয়েস্ট টেমপ্লেট](/.github/pull_request_template.md) অন্তর্ভুক্ত আছে।
