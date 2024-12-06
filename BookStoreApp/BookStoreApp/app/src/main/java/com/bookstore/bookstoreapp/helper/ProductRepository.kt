package com.bookstore.bookstoreapp.helper

import com.bookstore.bookstoreapp.R
import com.bookstore.bookstoreapp.data.Product

    object ProductRepository {
        private val products = listOf(
            Product(1,R.drawable.nutuk_history, "Nutuk", "Mustafa Kemal Atatürk", "150 ", "History",
                "The speech covered the events between the start of the Turkish War of Independence on 19 May 1919, " +
                        "and the foundation of the Republic of Turkey, in 1923. It took thirty-six hours (on a 6 day span) to be read " +
                        "by Atatürk, and became a foundation of Kemalist historiography."),
            Product(2,R.drawable.cankaya_history, "Çankaya", "Falih Rıfkı Atay", "140 ", "History",
                "'Let me inform you that Atatürk was a very calculated man who knew what he was doing, how he would do it, " +
                        "whom he would make do what, and how he would use people in different places. You may criticize the things he " +
                        "has done. However, it is an undeniable fact that he was a leader who, above all, was \"loyal to himself\" and " +
                        "focused solely on achieving his own goals, beyond his friendships, closeness, and supposed confidences." +
                        " -Falih Rıfkı Atay-"),
            Product(3,R.drawable.devlet_history, "Devlet-i Aliyye", "Halil İnalcık", "150 ", "History",
                "Devlet-i 'Aliyye is the product of over half a century of work by one of the most prominent figures in " +
                        "Ottoman historiography of our time, Halil İnalcık. The first volume of this work focuses on the transformation " +
                        "of the Ottoman State from a principality into a powerful and established empire that ruled over the Middle East " +
                        "and the Balkans."),
            Product(4,R.drawable.enver_history, "Enver Paşa", "Mustafa Çolak", "145 ", "History",
                        "Enver Pasha embodied the characteristics of the era he lived in and thought in accordance with the " +
                        "spirit of the time. Therefore, like every historical event and figure, Enver Pasha must be examined " +
                        "within the context of the features of his time. Only then will his personality and actions be better understood."),
            Product(5,R.drawable.yakin_tarih_history, "Yakın Tarihin Gerçekleri", "İlber Ortaylı", "155 TL", "History",
                "A unique work to read about the reasons for the Ottoman Empire's decline, nationalist movements, the Italo-Turkish " +
                        "War, the Balkan Wars, World War I, and Mustafa Kemal Atatürk and his friends who established a republic " +
                        "rising from the ashes..."),
            Product(6,R.drawable.osmanli_history, "Osmanlı Tarihi", "Erhan Afyoncu", "155 ", "History",
                "\n" +
                        "In the last 50-60 years, Ottoman historiography has made significant progress worldwide. However, the results " +
                        "of academic research have not reached the public as much in our country. In this book, while the main outlines of" +
                        " Ottoman history are presented, the information derived from academic research has been used, but the topics " +
                        "have been conveyed in an understandable style."),
            Product(7,R.drawable.sherlock_mystery, "Sherlock Holmes", "Arthur Conan Doyle", "200 ", "Mystery",
                "Crime files always come down to this point. After months of committing a crime, a man is suspected. His clothes " +
                        "or garments are examined, and brown stains are found on them. Are these blood stains, mud marks, rust stains, or " +
                        "what? This question has confused many experts. Because there was no reliable test. Now we have Sherlock Holmes' " +
                        "test, and there will be no more difficulty."),
            Product(8,R.drawable.gonegilr_mystery, "Gone Girl", "Gillian Fylnn", "180 ", "Mystery",
                "These are the questions Nick Dunne finds himself asking on the morning of his fifth wedding anniversary when " +
                        "his wife Amy suddenly disappears. The police suspect Nick. Amy's friends reveal that she was afraid of him, " +
                        "that she kept secrets from him. He swears it isn't true. A police examination of his computer shows " +
                        "strange searches. He says they weren't made by him. And then there are the persistent calls on his mobile phone.\n" +
                        "\n" +
                        "So what did happen to Nick's beautiful wife?"),
            Product(9,R.drawable.istanbul_mystery, "İstanbul Hatırası", "Ahmet Ümit", "140 ", "Mystery",
                "\"Istanbul Memorial\" is a novel by Ahmet Ümit, known for his mastery in providing a rich background within " +
                        "crime fiction. This novel, like his others, is centered around a series of murders. However, what sets this " +
                        "book apart from a typical crime novel are many unique features. With its rich cast of characters, Istanbul " +
                        "Memorial brings together Istanbulites from various backgrounds, offering a complex structure enriched with " +
                        "underlying subplots.\n"),
            Product(10,R.drawable.koleksiyoncu_mystery, "Koleksiyoncu", "John Fowles", "135 ", "Mystery",
                "The Collector is the first novel by John Fowles, one of the leading writers of English literature. " +
                        "It was unfortunately rejected by many publishing houses, but when it was finally published, it brought him " +
                        "the fame he enjoys today. It is the precursor to masterpieces such as The French Lieutenant's Woman, " +
                        "The Magus, Creature, and Mantissa.\n"),
            Product(11,R.drawable.kuzular_mystery, "Kuzuların Sessizliği", "Thomas Harris", "105 ", "Mystery",
                "A serial killer known as Buffalo Bill is targeting certain women. The killer has a specific goal, but since " +
                        "the bodies are found in different states, no one can grasp the connection. Clarice Starling, a young intern " +
                        "at the FBI Academy, is surprised when she is called by Jack Crawford from the Bureau's Behavioral Science Division. " +
                        "Her task is to meet Dr. Hannibal Lecter, a highly intelligent psychiatrist and terrifying killer, " +
                        "who is held at the Baltimore Psychiatric Hospital and closely monitored for his criminal insanity. " +
                        "It is believed that Lecter's insights into the minds of killers could help trace Buffalo Bill and catch him."),
            Product(12,R.drawable.woods_mystery, "In The Woods", "Tana French", "105 TL", "Mystery",
                "As dusk approaches a small Dublin suburb in the summer of 1984, mothers begin to call their children home. " +
                        "But on this warm evening, three children do not return from the dark and silent woods. When the police arrive, " +
                        "they find only one of the children. He is gripping a tree trunk in terror, wearing blood-filled sneakers and unable " +
                        "to recall a single detail of the previous hours."),
            Product(13,R.drawable.dune_sciencefic, "Dune", "Frank Herbert", "170 ", "Science Fiction",
                "Set on the desert planet Arrakis, Dune is the story of the boy Paul Atreides, heir to a noble family tasked with ruling " +
                        "an inhospitable world where the only thing of value is the “spice” melange, a drug capable of extending life and " +
                        "enhancing consciousness."),
            Product(14,R.drawable.cesur_sciencefic, "Cesur Yeni Dünya", "Aldous Huxley", "145 ", "Science Fiction",
                "Brave New World takes us to the year \"632 after Ford.\" In this world, brave individuals are produced at the " +
                        "Central London Hatchery and Conditioning Centre, where the words \"Community, Identity, Stability\" are displayed " +
                        "at the entrance. Since fertilization is considered forbidden and shameful for women, \"motherhood\" and \"fatherhood\" " +
                        "are viewed as pornographic concepts. Social stability is ensured through conditioning, with hypnopaedic sleep-teaching " +
                        "as the primary method. In the process of hypnopaedia, everyone is happy; everyone works, and everyone has fun. " +
                        "\"Everyone is for everyone.\"\n"),
            Product(15,R.drawable.fahrenheit_sciencefic, "Fahrenheit451", "Ray Bradbury", "90 ", "Science Fiction",
                "Ray Bradbury is not only one of the masters of science fiction but also of fantasy literature and horror in the " +
                        "twentieth century. He is perhaps the first writer to prove that science fiction can also be \"good literature.\" " +
                        "Fahrenheit 451, which became a classic upon its publication and is one of the four foundational books of dystopian " +
                        "literature, is a twentieth-century masterpiece.\n"),
            Product(16,R.drawable.otostopcu_sciencefic, "Otostopçunun Galaksi Rehberi", "Douglas Adams", "110 ", "Science Fiction",
                "Galaksinin Batı Sarmal Kolu’nun bir ucunda, haritası bile çıkarılmamış ücra bir köşesinde, gözlerden uzak, küçük ve sarı " +
                        "bir güneş vardır. Bu güneşin yörüngesinde, tamamıyla önemsiz ve mavi-yeşil renkli, küçük bir gezegen döner. Gezegenin maymun " +
                        "soyundan gelen canlıları öyle ilkeldir ki dijital kol saatinin hâlâ çok etkileyici bir buluş olduğunu düşünürler. Bu gezegenin " +
                        "şöyle bir sorunu vardı: Üzerinde yaşayan halkın büyük bölümü çoğu zaman mutsuzdu."),
            Product(17,R.drawable.exhala_sciencefic, "Exhalation", "Ted Chiang", "110 ", "Science Fiction",
                "In \"The Merchant and the Alchemist's Gate,\" a portal through time forces a fabric seller in ancient Baghdad to grapple with " +
                        "past mistakes and second chances. In \"Exhalation,\" an alien scientist makes a shocking discovery with ramifications that " +
                        "are literally universal. In \"Anxiety Is the Dizziness of Freedom,\" the ability to glimpse into alternate universes " +
                        "necessitates a radically new examination of the concepts of choice and free will."),
            Product(18,R.drawable.neuro_sciencefic, "Neuromancer", "William Gibson", "110 ", "Science Fiction",
                "Hotwired to the leading edges of art and technology, Neuromancer is a cyberpunk, science fiction masterpiece—a " +
                        "classic that ranks with 1984 and Brave New World as one of the twentieth century’s most potent visions of the " +
                        "future.\n"),
            Product(19,R.drawable.delilige_phylosopy, "Deliliğe Övgü", "Erasmus", "125 ", "Philosophy",
                "While traveling to England, he designed and wrote In Praise of Folly at Thomas More's house, criticizing the " +
                        "intellectuals of the time. With a satirical tone, he ridiculed teachers, priests, theologians, philosophers, merchants, " +
                        "lawyers, rulers, saints, and everyone who considered themselves intelligent.\n"),
            Product(20,R.drawable.devlet_phylosopy, "Devlet", "Platon", "130 ", "Philosophy",
                "Presented in the form of a dialogue between Socrates and three different interlocutors, this classic text is " +
                        "an enquiry into the notion of a perfect community and the ideal individual within it. During the conversation, " +
                        "other questions are raised: what is goodness?; what is reality?; and what is knowledge? "),
            Product(21,R.drawable.kendime_phlosopy, "Kendime Düşünceler", "Marcus Aurelius", "140 ", "Philosophy",
                "In Meditations, he not only criticizes the Caesars and philosophers before him, but also questions himself, " +
                        "engaging in a process of self-reflection. Meditations, which became a foundation for later generations, church thinkers, " +
                        "and the Renaissance, is still a valuable source for understanding Stoic philosophy today.\n" ),
            Product(22,R.drawable.sofie_phlosopy, "Sofie'nin Dünyası", "Jostein Gaarder", "115 ", "Philosophy",
                "Sophie's World has been translated into nearly forty languages, including Korean, Russian, Japanese, and Arabic, " +
                        "since its publication in 1991, and has achieved the success of becoming a bestseller in every country it was published.\n"),
            Product(23,R.drawable.prens_philosophy, "Prens", "Nıccolo Machiavelli", "115 ", "Philosophy",
                "The Prince, political treatise by Niccolò Machiavelli, written in 1513.\n" +
                        "\n" +
                        "A short treatise on how to acquire power, create a state, and keep it, The Prince represents Machiavelli’s " +
                        "effort to provide a guide for political action based on the lessons of history and his own experience as a " +
                        "foreign secretary in Florence."),
            Product(24,R.drawable.sokrates_philosophy, "Sokratesin Savunması", "Platon", "115 ", "Philosophy",
                "This book contains four complementary dialogues. In the first dialogue, Euthyphro, the events before Socrates' " +
                        "trial are described, and his beliefs are discussed in relation to the charge of impiety. Apology presents the " +
                        "trial process. In Crito, the aftermath of the judgment is narrated, and the principles that a citizen should " +
                        "respect are debated. In one of Plato's most poetic works, Phaedo, Socrates' last day is depicted, " +
                        "reflecting his thoughts on the soul.\n" ),
            Product(25,R.drawable.frank_horror, "Frankenstein", "Mary Shelley", "95 ", "Horror",
                "After Percy Bysshe Shelley's death in a boating accident, Mary returned to England in 1823, where she continued" +
                        " her life as a professional writer until her death in 1851. Best known for Frankenstein, Mary Shelley was also " +
                        "the author of novels such as Lodore, Falkner, Perkin Warbeck, and the futuristic novel The Last Man, which " +
                        "examines the gradual extinction of humanity.\n" ),
            Product(26,R.drawable.medyum_horror, "Medyum", "Stephen King", "115 ", "Horror",
                "Jack Torrance's starting work at the Overlook Hotel was a unique opportunity for him to turn a new page in his " +
                        "life. While serving as the caretaker of this old hotel, which was deserted by guests at the end of the season, " +
                        "he would be able to spend plenty of time with his family and work on his novel. But as the harsh winter weather " +
                        "began to set in, this peaceful place became even more isolated...\n" ),
            Product(27,R.drawable.obsesif_horror, "Obsesif", "Chevy Stevens", "90 ", "Horror",
                "This is not just the story of a woman's disappearance, but also the story of her return to herself, to independence, " +
                        "to life. An unforgettable story that will make you feel as if the light is piercing your skin like needles. " +
                        "Gillian Flynn, author of Gone Girl -"),
            Product(28,R.drawable.sizofren_horror, "Şizofren", "John Katzenbach", "125 ", "Horror",
                "Twenty years ago, Francis Petrel was sent to a psychiatric hospital by his family, his will disregarded, and " +
                        "kept there for a long period of time. That was until a series of murders occurred, and the hospital doors were " +
                        "sealed. Years later, a series of events, concealed and nearly forgotten, are revisited with the request for an " +
                        "investigation by a determined detective. Although Francis has returned to reality, he still hears voices, and can" +
                        " only silence them with medication.\n" ),
            Product(29,R.drawable.tavsan_horror, "Lanetli Tavşan", "Bora Chung", "125 ", "Horror",
                "The Cursed Rabbit brings together the stories of South Korean author Bora Chung, blending elements of magical " +
                        "realism, horror, fairy tale, and science fiction. Chung delicately explores the possibilities of fantasy and the " +
                        "surreal, revealing the violence and terror caused by capitalism and patriarchy in modern society.\n"),
            Product(30,R.drawable.dracula_horror, "Dracula", "Bram Stoker", "125 ", "Horror",
                "In Dracula, Bram Stoker created one of the great masterpieces of the horror genre, brilliantly evoking a " +
                        "nightmare world of vampires and vampire hunters and also illuminating the dark corners of Victorian sexuality and desire.\n"),
        )

        // Get all products
        fun getAllProducts(): List<Product> = products

        // Get products by category
        fun getProductsByCategory(category: String): List<Product> {
            return products.filter { it.category == category }
        }
        fun getProductById(id: Int): Product? {
            return products.find { it.bookId == id }
        }
    }