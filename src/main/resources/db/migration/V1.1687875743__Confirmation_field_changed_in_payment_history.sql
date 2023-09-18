alter table public.payment_history drop column is_confirmed;

alter table public.payment_history add column payment_status varchar;

alter table public.transactions_history add column created_at timestamp;