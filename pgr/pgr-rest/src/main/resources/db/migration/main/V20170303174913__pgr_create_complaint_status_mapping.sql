CREATE TABLE egpgr_complaintstatus (
    id bigint NOT NULL,
    name character varying(25),
    version bigint,
    tenantid character varying(256) not null
);
CREATE SEQUENCE seq_egpgr_complaintstatus
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE  egpgr_complaintstatus
    ADD CONSTRAINT pk_complaintstatus PRIMARY KEY (id);
alter table egpgr_complaintstatus add constraint uk_complaintstatus_name_tenant unique (name,tenantid);


CREATE TABLE egpgr_complaintstatus_mapping (
    id bigint NOT NULL,
    role_id bigint NOT NULL,
    current_status_id bigint NOT NULL,
    orderno bigint,
    show_status_id bigint,
    version bigint,
    tenantid character varying(256) not null
);


CREATE SEQUENCE seq_egpgr_complaintstatus_mapping
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE egpgr_complaintstatus_mapping
    ADD CONSTRAINT pk_complaintstatus_mapping PRIMARY KEY (id);
    
ALTER TABLE  egpgr_complaintstatus_mapping
    ADD CONSTRAINT fk_show_status_id FOREIGN KEY (show_status_id) REFERENCES egpgr_complaintstatus(id);

ALTER TABLE  egpgr_complaintstatus_mapping
    ADD CONSTRAINT fk_current_status_id FOREIGN KEY (current_status_id) REFERENCES egpgr_complaintstatus(id);

alter table egpgr_complaintstatus_mapping add constraint uk_complaintstatus_mapping_id_tenant unique (id,tenantid); 
