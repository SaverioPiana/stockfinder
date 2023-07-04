# Stockfinder

Progetto personale SIW 2023 Luca Foresti, Saverio Piana
- Live Vertion : https://app-stockfinder-romatre.azurewebsites.net
# Setup :
- Creare il file "application-dev.properties" nella cartella "resources" e aggiungere "alphavantage.apiKey={apikey}"
- La chiave api si puo reperire qui https://www.alphavantage.co/support/#api-key
# Esperienze Utente :

### UTENTE NON REGISTRATO:
- **Ricerca stock nel sistema:** un utente non registrato può cercare prodotti specifici tramite parole chiave (acronimo stock). Il sistema restituisce una lista di prodotti corrispondenti ai criteri di ricerca.
> Caso d'uso: l'utente utilizza la barra di ricerca per trovare una stock e visualizzarne i dettagli.

- **Mostra tutte le stock del sistema:** un utente non registrato può visualizzare l'intero inventario di stock disponibili.
> Caso d'uso: l'utente naviga nella sezione "Stock" del sito e visualizza la lista completa dei prodotti.

- **Visualizzazione del dettaglio delle stock**, comprese informazioni come prezzo, quantità disponibile, e dati sul prodotto stesso (descrizione, foto, ecc.): un utente non registrato può accedere ai dettagli di ogni prodotto, compresi prezzo, quantità, descrizione, foto e commenti.
> Caso d'uso: l'utente clicca sul nome o sull'immagine di un prodotto per visualizzarne i dettagli.

- **Visualizzazione dei commenti degli utenti registrati:** un utente non registrato può leggere i commenti degli utenti registrati per ogni stock nella pagina di dettaglio della stock.
> Caso d'uso: l'utente clicca su una specifica stock  per leggere le recensioni degli altri utenti.

### UTENTE REGISTRATO:
- **Registrazione tramite email e password o OAuth:** un utente registrato può registrarsi al sito web tramite email/username e password, oppure utilizzando un servizio OAuth, come Google o Facebook, per semplificare il processo di registrazione.
> Caso d'uso: l'utente accede alla pagina di registrazione e inserisce i dati richiesti.

- **Personalizzazione del profilo:** un utente registrato può personalizzare il proprio profilo aggiungendo informazioni come nickname, nome, cognome, data di nascita e foto.
> Caso d'uso: l'utente accede alla pagina del profilo e aggiorna le informazioni personali.

- **Aggiunta di denaro al proprio account:** un utente registrato può aggiungere denaro al proprio account dalla pagina “Deposita” e inserisce quanto vuole essere depositato nel suo account.
> Caso d'uso: l'utente accede alla pagina del portafoglio e seleziona il metodo di pagamento preferito per aggiungere denaro.

- **Compravendita di stock:** un utente registrato può comprare e vendere prodotti tramite il sito web. L'utente può effettuare un acquisto di stock (se ne possiede i fondi)  e visualizzare la cronologia degli acquisti.
> Caso d'uso: l'utente naviga nella sezione "Stock",sceglie una stock ed  effettua l'ordine in quantità di stock volute e può visualizza la cronologia degli acquisti di quella singola stock.

- **Visualizzazione del portafoglio delle stock (inclusi dettagli come quantità possedute e valore attuale):** un utente registrato può visualizzare il proprio portafoglio di prodotti, comprese informazioni come quantità possedute e valore attuale dei prodotti.
> Caso d'uso: l'utente accede alla pagina del portafoglio per visualizzare le proprie azioni e il loro valore attuale.

- **Creazione di commenti:** un utente registrato può scrivere fino a 5 commenti totali sui prodotti presenti sul sito, con un massimo di 1 commento per prodotto.
> Caso d'uso: l'utente accede alla pagina del prodotto e scrive il proprio commento.

- **Reset del proprio portafoglio:** un utente registrato può resettare completamente il proprio portafoglio, eliminando tutte le azioni possedute e il denaro.
> Caso d'uso: l'utente accede alla pagina del portafoglio e seleziona l'opzione per resettare il portafoglio.

### UTENTE ADMIN:
- **Creazione di altri admin:** un admin può creare altri admin, consentendo loro di eseguire le stesse funzioni.
> Caso d'uso: l'admin accede alla pagina degli utenti e crea un nuovo admin.

- **Aggiunta di stock al sistema:** un admin può aggiungere nuovi prodotti al sistema, comprese informazioni come prezzo, quantità disponibile, descrizione e foto.
> Caso d'uso: l'admin accede alla pagina di gestione degli stock e aggiunge un nuovo prodotto.

- **Rimozione dei commenti degli utenti registrati:** un admin può rimuovere i commenti degli utenti registrati sui prodotti presenti sul sito.
> Caso d'uso: l'admin accede alla pagina del prodotto e rimuove un commento.

- **Reset del portafoglio di un utente registrato:** un admin può resettare completamente il portafoglio di un utente registrato, eliminando tutte le azioni possedute e il denaro.
> Caso d'uso: l'admin accede alla pagina degli utenti, seleziona un utente registrato e resetta il suo portafoglio.

# Modello di dominio semplificato:

Il modello di dominio descrive le entità e le relazioni all'interno del sistema:

- Utente: rappresenta un utente del sistema, con attributi come ID utente, username, email, password, ruolo (registrato o admin), nickname, nome, cognome, data di nascita, foto e saldo.

- Stock: rappresenta un prodotto nel sistema, con attributi come ID prodotto, nome, descrizione, foto, prezzo, quantità disponibile e valore attuale.

- Commento: rappresenta un commento di un utente registrato su un prodotto, con attributi come ID commento, testo, data, ID utente e ID prodotto.

- Ordine: rappresenta un ordine effettuato da un utente registrato, con attributi come ID ordine, data, ID utente, stato dell'ordine e lista dei prodotti.

- Metodo di pagamento: rappresenta un metodo di pagamento utilizzato da un utente per aggiungere denaro al proprio account, con attributi come ID metodo di pagamento, nome, descrizione e saldo.

- Transazione: rappresenta una transazione di un utente, con attributi come ID transazione, data, ID utente, importo e tipo di transazione (deposito o acquisto).

- Admin: rappresenta un admin del sistema, con attributi come ID admin, username, email e password.

 ### Relazioni:

Le relazioni tra le entità sono le seguenti:

- Un utente può avere molti commenti su diversi prodotti e può effettuare molti ordini.
- Un prodotto può avere molti commenti da diversi utenti e può essere presente in molti ordini.
- Un ordine può includere molti prodotti e può essere effettuato da un solo utente.
- Un utente può aggiungere denaro al proprio saldo utilizzando molti metodi di pagamento e può effettuare molte transazioni.
- Un metodo di pagamento può essere utilizzato da molti utenti e può essere presente in molte transazioni.
- Un admin può creare molti altri admin.
