-- ============================
-- USUÁRIOS
-- ============================

INSERT INTO tb_usuario (id, login, senha, tipo_usuario) VALUES (1, 'bcq', '123456', 'MOD');
INSERT INTO tb_usuario (id, login, senha, tipo_usuario) VALUES (2, 'boris', '123456', 'USER');

-- ============================
-- POSTAGENS
-- ============================

INSERT INTO tb_postagem (id, conteudo, usuario_id) VALUES (1, 'hahahahahahahahaflowershahahaha', 1);

-- ============================
-- COMENTARIOS
-- ============================

INSERT INTO tb_comentario (id, conteudo, postagem_id, usuario_id) VALUES (1, 'jaorange', 1, 2);


-- ============================
-- GALERIA
-- ============================

INSERT INTO tb_galeria (id, img_link) VALUES (1, 'https://img.cdndsgni.com/preview/13786137-m.jpg');
INSERT INTO tb_galeria (id, img_link) VALUES (2, 'https://img.cdndsgni.com/preview/13786137-m.jpg');

