create table public.service_provider (
                                         id bigserial primary key,
                                         title varchar not null
);

create table public.service_group (
                                      id bigserial primary key,
                                      title varchar not null,
                                      service_provider_id bigint references public.service_provider(id) -- m20 sp
);

create table public.service (
                                id bigserial primary key,
                                title varchar not null,
                                commission integer not null,
                                min_payment_amount integer not null,
                                service_group_id bigint references public.service_group(id) -- m2o sg
);

create table public.payment_history(
                                       id bigserial primary key,
                                       client_full_name varchar not null,
                                       service_id bigint references public.service(id), -- m2o
                                       client_details varchar not null,
                                       payment_amount integer not null,
                                       is_confirmed boolean not null default false
);

create table public.provider_details(
                                        id bigserial primary key,
                                        bank_account varchar not null,
                                        service_provider_id bigint references public.service_provider(id), --m2o
                                        service_group_id bigint references public.service_group(id) --m2o
);

create table transactions_history(
                                     id bigserial primary key,
                                     payment_amount integer not null,
                                     service_id bigint references public.service(id), --m2o
                                     provider_details_id bigint references public.provider_details(id) --m2o
);