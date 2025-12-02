package com.levelup.gaming.config;

import com.levelup.gaming.models.*;
import com.levelup.gaming.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private ProductRepository productRepository;

        @Autowired
        private EventRepository eventRepository;

        @Autowired
        private BlogRepository blogRepository;

        @Autowired
        private VideoRepository videoRepository;

        @Autowired
        private RewardRepository rewardRepository;

        @Override
        public void run(String... args) throws Exception {
                seedUsers();
                seedProducts();
                seedEvents();
                seedBlogPosts();
                seedVideos();
                seedRewards();
        }

        private void seedUsers() {
                if (userRepository.count() == 0) {
                        User u1 = new User();
                        u1.setId("u1");
                        u1.setName("Administrador Principal");
                        u1.setEmail("admin@levelup.com");
                        u1.setPassword("admin123");
                        u1.setRut("123456789");
                        u1.setAge(35);
                        u1.setRole("admin");
                        u1.setHasDuocDiscount(true);
                        u1.setPoints(500);
                        u1.setReferralCode("ADMIN1000");
                        u1.setActive(true);
                        u1.setAddress(new Address("Padre Alonso de Ovalle 1586", "Santiago", "Metropolitana"));

                        userRepository.save(u1);
                        System.out.println("Users seeded successfully!");
                }
        }

        private void seedProducts() {
                if (productRepository.count() == 0) {
                        Product p1 = new Product();
                        p1.setId("JM001");
                        p1.setName("Catan");
                        p1.setDescription(
                                        "Un clásico juego de estrategia donde los jugadores compiten por colonizar y expandirse en la isla de Catan. Perfecto para 3-4 jugadores, con una duración promedio de 60-90 minutos.");
                        p1.setPrice(29990);
                        p1.setImageUrl("/images/juego-catan.png");
                        p1.setRating(4.8);
                        p1.setNumReviews(156);
                        p1.setTopSelling(true);
                        p1.setCountInStock(20);
                        p1.setCategory("Juegos de Mesa");
                        p1.setSpecifications(
                                        "{\"Jugadores\": \"3-4\", \"Tiempo\": \"60-90 min\", \"Estrategia\": \"Alta\"}");
                        p1.setActive(true);

                        Review r1_1 = new Review("r1-1", "Carlos M.", 5,
                                        "Excelente juego, muy entretenido para jugar en familia.",
                                        LocalDateTime.parse("2024-11-15T00:00:00"));
                        Review r1_2 = new Review("r1-2", "María P.", 5,
                                        "Mi juego favorito! Siempre es diferente cada partida.",
                                        LocalDateTime.parse("2024-11-10T00:00:00"));
                        p1.setReviews(Arrays.asList(r1_1, r1_2));
                        Product p2 = new Product();
                        p2.setId("JM002");
                        p2.setName("Carcassonne");
                        p2.setDescription(
                                        "Un juego de colocación de fichas donde los jugadores construyen el paisaje alrededor de la fortaleza medieval. Es ideal para 2-5 jugadores.");
                        p2.setPrice(24990);
                        p2.setImageUrl("/images/juego-carcassonne.png");
                        p2.setRating(4.5);
                        p2.setNumReviews(89);
                        p2.setTopSelling(false);
                        p2.setCountInStock(15);
                        p2.setCategory("Juegos de Mesa");
                        p2.setSpecifications(
                                        "{\"Jugadores\": \"2-5\", \"Tiempo\": \"35 min\", \"Tipo\": \"Colocación de Fichas\"}");
                        p2.setActive(true);
                        Product p3 = new Product();
                        p3.setId("AC001");
                        p3.setName("Controlador Inalámbrico Xbox Series X");
                        p3.setDescription(
                                        "Ofrece una experiencia de juego cómoda con botones mapeables y una respuesta táctil mejorada.");
                        p3.setPrice(59990);
                        p3.setImageUrl("/images/controlador-xbox.png");
                        p3.setRating(4.9);
                        p3.setNumReviews(215);
                        p3.setTopSelling(true);
                        p3.setCountInStock(40);
                        p3.setCategory("Accesorios");
                        p3.setSpecifications(
                                        "{\"Conectividad\": \"Inalámbrica/Bluetooth\", \"Batería\": \"AA/Recargable\"}");
                        p3.setActive(true);

                        Review r3_1 = new Review("r3-1", "Pedro A.", 5, "Es el mejor control que he tenido.",
                                        LocalDateTime.parse("2024-11-25T00:00:00"));
                        p3.setReviews(Arrays.asList(r3_1));
                        Product p4 = new Product();
                        p4.setId("AC002");
                        p4.setName("Auriculares Gamer HyperX Cloud II");
                        p4.setDescription(
                                        "Proporcionan un sonido envolvente de calidad con un micrófono desmontable y almohadillas de espuma viscoelástica.");
                        p4.setPrice(79990);
                        p4.setImageUrl("/images/auriculares-hyperx.png");
                        p4.setRating(4.7);
                        p4.setNumReviews(190);
                        p4.setTopSelling(true);
                        p4.setCountInStock(30);
                        p4.setCategory("Accesorios");
                        p4.setSpecifications("{\"Sonido\": \"7.1 Surround\", \"Micrófono\": \"Desmontable\"}");
                        p4.setActive(true);

                        Review r4_1 = new Review("r4-1", "Felipe T.", 5, "El sonido 7.1 es increíble.",
                                        LocalDateTime.parse("2024-11-24T00:00:00"));
                        p4.setReviews(Arrays.asList(r4_1));
                        Product p5 = new Product();
                        p5.setId("CO001");
                        p5.setName("PlayStation 5");
                        p5.setDescription(
                                        "La consola de última generación de Sony, que ofrece gráficos impresionantes en 4K y tiempos de carga ultrarrápidos.");
                        p5.setPrice(549990);
                        p5.setImageUrl("/images/playstation-5-console.png");
                        p5.setRating(4.9);
                        p5.setNumReviews(350);
                        p5.setTopSelling(true);
                        p5.setCountInStock(5);
                        p5.setCategory("Consolas");
                        p5.setSpecifications(
                                        "{\"CPU\": \"8x Zen 2 Cores\", \"GPU\": \"10.28 TFLOPS\", \"SSD\": \"825GB Custom\"}");
                        p5.setActive(true);

                        Review r5_1 = new Review("r5-1", "Isabel B.", 5,
                                        "Una consola increíble. Los gráficos son de otro nivel.",
                                        LocalDateTime.parse("2024-11-26T00:00:00"));
                        p5.setReviews(Arrays.asList(r5_1));
                        Product p6 = new Product();
                        p6.setId("CG001");
                        p6.setName("PC Gamer ASUS ROG Strix");
                        p6.setDescription(
                                        "Un potente equipo diseñado para los gamers más exigentes, equipado con los últimos componentes.");
                        p6.setPrice(1299990);
                        p6.setImageUrl("/images/pc-gamer-asus.png");
                        p6.setRating(5.0);
                        p6.setNumReviews(45);
                        p6.setTopSelling(false);
                        p6.setCountInStock(3);
                        p6.setCategory("Computadores");
                        p6.setSpecifications(
                                        "{\"Procesador\": \"Intel Core i9\", \"GPU\": \"RTX 4080\", \"RAM\": \"32GB\"}");
                        p6.setActive(true);

                        Review r6_1 = new Review("r6-1", "Daniel P.", 5,
                                        "Una bestia. Corre todos mis juegos en ultra.",
                                        LocalDateTime.parse("2024-11-28T00:00:00"));
                        p6.setReviews(Arrays.asList(r6_1));
                        Product p7 = new Product();
                        p7.setId("SG001");
                        p7.setName("Silla Gamer Secretlab Titan");
                        p7.setDescription(
                                        "Diseñada para el máximo confort, con un soporte ergonómico y reclinación multi-ángulo.");
                        p7.setPrice(349990);
                        p7.setImageUrl("/images/silla-gamer-secretlab.png");
                        p7.setRating(4.8);
                        p7.setNumReviews(110);
                        p7.setTopSelling(false);
                        p7.setCountInStock(12);
                        p7.setCategory("Accesorios");
                        p7.setSpecifications(
                                        "{\"Soporte\": \"Lumbar incorporado\", \"Material\": \"Cuero sintético\"}");
                        p7.setActive(true);

                        Review r7_1 = new Review("r7-1", "José M.", 5, "Es increíblemente cómoda.",
                                        LocalDateTime.parse("2024-11-23T00:00:00"));
                        p7.setReviews(Arrays.asList(r7_1));
                        Product p8 = new Product();
                        p8.setId("MS001");
                        p8.setName("Mouse Gamer Logitech G502 HERO");
                        p8.setDescription(
                                        "Con sensor de alta precisión y botones personalizables, ideal para gamers que buscan control preciso.");
                        p8.setPrice(49990);
                        p8.setImageUrl("/images/mouse-logitech-g502.png");
                        p8.setRating(4.9);
                        p8.setNumReviews(250);
                        p8.setTopSelling(false);
                        p8.setCountInStock(60);
                        p8.setCategory("Accesorios");
                        p8.setSpecifications(
                                        "{\"Sensor\": \"HERO 25K\", \"DPI\": \"25600\", \"Peso\": \"Ajustable\"}");
                        p8.setActive(true);

                        Review r8_1 = new Review("r8-1", "Gabriel N.", 5, "El mejor mouse para gaming.",
                                        LocalDateTime.parse("2024-11-27T00:00:00"));
                        p8.setReviews(Arrays.asList(r8_1));

                        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8));
                        System.out.println("Products seeded successfully!");
                }
        }

        private void seedEvents() {
                if (eventRepository.count() == 0) {
                        Event e1 = new Event("e1", "Torneo de eSports Level-Up (League of Legends)",
                                        LocalDate.parse("2025-11-10"), LocalTime.parse("18:00"),
                                        "Sede Padre Alonso de Ovalle, Santiago",
                                        "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3329.089208314821!2d-70.66055698782124!3d-33.44698229722517!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x9662c507f91be917%3A0xcfac72a68dd4b986!2sDuoc%20UC%20-%20Sede%20Padre%20Alonso%20de%20Ovalle!5e0!3m2!1ses!2scl!4v1764624754878!5m2!1ses!2scl",
                                        "https://maps.app.goo.gl/9anLTjf657Yq2WqF6", "Hall Central");
                        Event e2 = new Event("e2", "Feria de Tecnología Gaming (LAN Party)",
                                        LocalDate.parse("2025-12-05"), LocalTime.parse("10:00"),
                                        "Movistar Arena, Santiago",
                                        "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3328.4908921327205!2d-70.66427528782073!3d-33.46256809802346!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x9662c51b935c0c23%3A0xfe177137c9c9c6c8!2sMovistar%20Arena!5e0!3m2!1ses!2scl!4v1764624874136!5m2!1ses!2scl",
                                        "https://maps.app.goo.gl/2hSqXAJdSg6fF24B7",
                                        "Movistar Arena");
                        eventRepository.saveAll(Arrays.asList(e1, e2));
                        System.out.println("Events seeded successfully!");
                }
        }

        private void seedBlogPosts() {
                if (blogRepository.count() == 0) {
                        Blog b1 = new Blog("1", "Los 5 Mejores Juegos de 2025: La Batalla Final por el GotY.",
                                        "Analizamos los lanzamientos más explosivos de la nueva era. Desde el épico regreso de sagas de rol hasta los shooters que dominarán la escena competitiva. ¿Vale la pena la espera? ¡Te lo contamos todo!",
                                        "\n            <p class=\"text-white\">La industria del gaming está entrando en una era dorada, y el 2025 promete ser un año de lanzamientos que redefinirán el género. Aquí, en Level-Up Gaming, hemos seleccionado los títulos que tienen el potencial de competir por el premio más grande: el Juego del Año (GotY).</p>\n            \n            <h4 class=\"mt-4\" style=\"color: #1E90FF;\">1. Chronos Gate: La Nueva Fantasía Épica</h4>\n            <p class=\"text-white\">Este RPG de mundo abierto no solo promete gráficos fotorrealistas, sino un sistema de combate que fusiona la acción rápida con la estrategia por turnos. La narrativa ramificada y las 300 horas de contenido principal lo convierten en el principal contendiente del género.</p>\n            \n            <h4 class=\"mt-4\" style=\"color: #1E90FF;\">2. Midnight Protocol (Shooter Táctico)</h4>\n            <p class=\"text-white\">El FPS que está revolucionando la escena competitiva. Su nuevo motor de destrucción y un sistema de clanes persistente aseguran una comunidad activa por años. Si buscas adrenalina y estrategia de equipo, esta es la elección.</p>\n\n            <h4 class=\"mt-4\" style=\"color: #1E90FF;\">3. Elden Ring: Shadow of the Erdtree (Expansión)</h4>\n            <p class=\"text-white\">Aunque es una expansión, su tamaño y ambición superan a muchos juegos completos. FromSoftware ha prometido nuevos biomas, jefes de dificultad legendaria y una profundización en la historia que dejará a los fans sin aliento.</p>\n            \n            <p class=\"mt-4\">\n            <strong style=\"color: #39FF14;\">Conclusión:</strong> El 2025 no es solo de secuelas; es de experiencias que exigen el máximo de tu hardware. ¡Prepara tus componentes!\n            </p>\n        ",
                                        "/images/mejores.png", "Leo \"The Analyst\"",
                                        LocalDateTime.parse("2025-09-07T00:00:00"));
                        Blog b2 = new Blog("2",
                                        "Guía Definitiva: Arma tu PC por Menos de $500K CLP (¡Potencia Garantizada!).",
                                        "Te enseñamos a maximizar el rendimiento con un presupuesto ajustado. La clave está en el procesador y encontrar componentes de segunda mano de calidad que te darán un rendimiento sorprendente.",
                                        "\n            <p class=\"text-white\">Armar un PC Gamer por menos de $500.000 CLP es un desafío que requiere astucia y conocimiento del mercado. Nuestro objetivo es alcanzar los 60 FPS estables en títulos AAA con configuraciones medias.</p>\n            \n            <h4 class=\"mt-4\" style=\"color: #1E90FF;\">Componentes Clave y Trucos de Ahorro</h4>\n            <ul>\n                <li class=\"text-white\"><strong>CPU (Ahorro Inteligente):</strong> Un AMD Ryzen 5 3600 (usado, perfecto para 1080p) o un Core i3-12100F (nuevo) son ideales. No te excedas aquí.</li>\n                <li class=\"text-white\"><strong>GPU (La Inversión Principal):</strong> Busca tarjetas gráficas de segunda mano, como una NVIDIA GTX 1660 Super o una AMD RX 580. Son las reinas del presupuesto.</li>\n                <li class=\"text-white\"><strong>RAM:</strong> 16GB DDR4 a 3200MHz. Menos de esto es inaceptable para el gaming moderno.</li>\n                <li class=\"text-white\"><strong>Almacenamiento:</strong> 500GB SSD NVMe. La velocidad es más importante que la capacidad inicial.</li>\n            </ul>\n            \n            <h4 class=\"mt-4\" style=\"color: #1E90FF;\">Errores a Evitar y Consejos de Montaje</h4>\n            <p class=\"text-white\">El error más común es comprar la fuente de poder (PSU) más barata. Una PSU certificada (80+ Bronze, 550W) es vital para la estabilidad. Además, asegúrate de que tu gabinete tenga buen flujo de aire, ¡o tus componentes se freirán en verano!</p>\n            \n            <p class=\"mt-4\">\n            <strong style=\"color: #39FF14;\">Consejo Final:</strong> Si tu presupuesto es estricto, prioriza la CPU y la RAM; siempre puedes mejorar la tarjeta gráfica más adelante.\n            </p>\n        ",
                                        "/images/partes.png", "Dani \"The Builder\"",
                                        LocalDateTime.parse("2025-10-28T00:00:00"));
                        Blog b3 = new Blog("3",
                                        "PS5 Slim vs. Xbox Series X: ¿Cuál Consola Deberías Comprar en 2026?",
                                        "Comparamos el poder bruto, la librería exclusiva y el valor del servicio Game Pass frente a la retroalimentación háptica de Sony. El veredicto que necesitas antes de invertir.",
                                        "\n            <p class=\"text-white\">Ambas consolas representan el pináculo del gaming de salón, pero se dirigen a jugadores con prioridades distintas. La diferencia ya no es tanto de potencia bruta (ambas son excelentes), sino de ecosistema.</p>\n            \n            <h4 class=\"mt-4\" style=\"color: #1E90FF;\">Ventaja de Catálogo: PlayStation 5</h4>\n            <p class=\"text-white\">PS5 mantiene la corona de los exclusivos de un jugador, como *God of War*, *Horizon* y *Final Fantasy XVI*. El DualSense (con su gatillo adaptativo y retroalimentación háptica) ofrece una inmersión que Xbox aún no iguala.</p>\n            \n            <h4 class=\"mt-4\" style=\"color: #1E90FF;\">Ventaja de Valor: Xbox Series X</h4>\n            <p class=\"text-white\">Xbox se enfoca en el valor a largo plazo gracias a su Game Pass, que incluye todos los juegos de Microsoft desde el día de lanzamiento. Para el jugador que quiere la biblioteca más grande sin gastar una fortuna, Xbox es el ganador indiscutible.</p>\n            \n            <p class=\"mt-4\">\n            <strong style=\"color: #39FF14;\">Veredicto Final:</strong> Si tu foco es la narrativa cinematográfica y las innovaciones del control, elige PS5. Si priorizas la cantidad de juegos disponibles y el juego online con amigos, Xbox Series X es la mejor opción.\n            </p>\n        ",
                                        "/images/tendencias.png", "Sistema",
                                        LocalDateTime.parse("2025-11-07T16:00:00"));
                        blogRepository.saveAll(Arrays.asList(b1, b2, b3));
                        System.out.println("Blogs seeded successfully!");
                }
        }

        private void seedVideos() {
                if (videoRepository.count() == 0) {
                        Video v1 = new Video("v1", "7 trucos para optimizar tu PC Gamer",
                                        "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/Hg_RGhCqqcM?si=0Ya-uLxbGLM1G7Ka\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>",
                                        true);
                        Video v2 = new Video("v2", "Como armar tu PC Gamer 2025",
                                        "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/VhUyEgSiUaw?si=lXKV8RrmRn9DDg4C\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>",
                                        true);
                        Video v3 = new Video("v3", "Top 5: Auriculares Gaming 2025",
                                        "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/6iW_iM-4T_0\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>",
                                        false);
                        videoRepository.saveAll(Arrays.asList(v1, v2, v3));
                        System.out.println("Videos seeded successfully!");
                }
        }

        private void seedRewards() {
                if (rewardRepository.count() == 0) {
                        Reward r1 = new Reward();
                        r1.setId("101");
                        r1.setType("Producto");
                        r1.setName("Taza Gamer Edición Limitada");
                        r1.setPointsCost(2800);
                        r1.setDescription("Canjea tus puntos por una taza exclusiva de Level-Up.");
                        r1.setActive(true);
                        r1.setSeason("Standard");
                        r1.setImageUrl("/images/taza-gamer.png");
                        r1.setDiscountType("NONE");
                        r1.setDiscountValue(null);
                        r1.setStockAvailable(null);
                        Reward r2 = new Reward();
                        r2.setId("102");
                        r2.setType("Descuento");
                        r2.setName("Cupón de $5.000 CLP");
                        r2.setPointsCost(6000);
                        r2.setDescription("Descuento aplicable a tu próxima compra.");
                        r2.setActive(true);
                        r2.setSeason("Standard");
                        r2.setImageUrl("/images/cupon.png");
                        r2.setDiscountType("FIXED_AMOUNT");
                        r2.setDiscountValue(5000.0);
                        r2.setStockAvailable(null);
                        Reward r3 = new Reward();
                        r3.setId("103");
                        r3.setType("Producto");
                        r3.setName("Mousepad RGB Extendido");
                        r3.setPointsCost(18000);
                        r3.setDescription("Mousepad amplio con iluminación RGB.");
                        r3.setActive(true);
                        r3.setSeason("Standard");
                        r3.setImageUrl("/images/mousepad-razer-chroma.png");
                        r3.setDiscountType("NONE");
                        r3.setDiscountValue(null);
                        r3.setStockAvailable(null);
                        Reward r4 = new Reward();
                        r4.setId("104");
                        r4.setType("Envio");
                        r4.setName("Envío Express Gratuito");
                        r4.setPointsCost(3500);
                        r4.setDescription("Cubre el costo de tu envío express (Valor: $5.000 CLP).");
                        r4.setActive(true);
                        r4.setSeason("Standard");
                        r4.setImageUrl("/images/truck.png");
                        r4.setDiscountType("FREE_SHIPPING");
                        r4.setDiscountValue(null);
                        r4.setStockAvailable(null);
                        Reward r5 = new Reward();
                        r5.setId("105");
                        r5.setType("Producto");
                        r5.setName("Polera Gamer Level-Up");
                        r5.setPointsCost(15000);
                        r5.setDescription("Polera con diseño del logo de la tienda.");
                        r5.setActive(true);
                        r5.setSeason("Standard");
                        r5.setImageUrl("/images/polera-gamer-personalizada.png");
                        r5.setDiscountType("NONE");
                        r5.setDiscountValue(null);
                        r5.setStockAvailable(null);
                        Reward r6 = new Reward();
                        r6.setId("106");
                        r6.setType("Descuento");
                        r6.setName("Cupón de 15% OFF");
                        r6.setPointsCost(35000);
                        r6.setDescription("Descuento del 15% para una compra grande.");
                        r6.setActive(true);
                        r6.setSeason("Standard");
                        r6.setImageUrl("/images/descuento.png");
                        r6.setDiscountType("PERCENTAGE");
                        r6.setDiscountValue(0.15); // 15%
                        r6.setStockAvailable(null);
                        Reward r7 = new Reward();
                        r7.setId("H01");
                        r7.setType("Descuento");
                        r7.setName("Cupón 20% OFF HALLOWEEN");
                        r7.setPointsCost(8000);
                        r7.setDescription("Cupón especial de temporada: 20% OFF. ¡Solo 50 disponibles!");
                        r7.setActive(true);
                        r7.setSeason("Halloween");
                        r7.setImageUrl("/images/halloween.png");
                        r7.setDiscountType("PERCENTAGE");
                        r7.setDiscountValue(0.20);
                        r7.setStock(50);
                        r7.setStockAvailable(50);

                        rewardRepository.saveAll(Arrays.asList(r1, r2, r3, r4, r5, r6, r7));
                        System.out.println("Rewards seeded successfully!");

                }
        }
}
