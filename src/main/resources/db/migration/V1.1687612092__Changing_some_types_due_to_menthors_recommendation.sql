alter table public.payment_history drop column client_details;
alter table public.payment_history alter column payment_amount type integer;

alter table public.service alter column commission type integer;

alter table public.transactions_history alter column payment_amount type integer;