db = db.getSiblingDB('timeforge');
db.createUser({
  user: 'timeforge_user',
  pwd: 'dbpassword123',
  roles: [{ role: 'readWrite', db: 'timeforge' }]
});