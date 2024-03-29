create table schema_brasilprev.categorias (id_categoria bigint not null, categoria varchar(255), primary key (id_categoria));
create table schema_brasilprev.clientes (id_cliente bigint not null, bairro varchar(255), cep varchar(255), cidade varchar(255), email varchar(255), estado varchar(255), nome varchar(255), rua varchar(255), senha varchar(255), primary key (id_cliente));
create table schema_brasilprev.pedido_itens (id_item bigint not null, produto varchar(255), quantidade integer, subtotal decimal(19,2), valor decimal(19,2), id_pedido bigint, id_produto bigint, primary key (id_item));
create table schema_brasilprev.pedidos (id_pedido bigint not null, data varchar(255), sessao varchar(255), status varchar(255), id_cliente bigint, primary key (id_pedido));
create table schema_brasilprev.produtos (id_produto bigint not null, descricao varchar(255), foto varchar(255), preco decimal(19,2), produto varchar(255), quantidade integer, id_categoria bigint, primary key (id_produto));
create sequence hibernate_sequence start with 1 increment by 1;
alter table schema_brasilprev.pedido_itens add constraint FKrgh7l50r1judvc3ok9y8sfrv3 foreign key (id_pedido) references schema_brasilprev.pedidos;
alter table schema_brasilprev.pedido_itens add constraint FK83345b0ifop00o8g4ttonqm77 foreign key (id_produto) references schema_brasilprev.produtos;
alter table schema_brasilprev.pedidos add constraint FKdnomiluem4t3x66t6b9aher47 foreign key (id_cliente) references schema_brasilprev.clientes;
alter table schema_brasilprev.produtos add constraint FKbq4e9crnlcsvm7ehu147wuavt foreign key (id_categoria) references schema_brasilprev.categorias;